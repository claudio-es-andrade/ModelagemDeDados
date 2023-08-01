package com.example.gameshopfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvoicesController {
    private int selectedRowIndex = -1;

    @FXML
    private TableView<Invoices> table;

    @FXML
    private TableColumn<Invoices, String> IdClm;

    @FXML
    private TableColumn<Invoices, String> NameClm;

    @FXML
    private TableColumn<Invoices, String> QuantityClm;

    @FXML
    private TableColumn<Invoices, String> GameClm;

    @FXML
    private TableColumn<Invoices, String> TotalClm;

    @FXML
    private TableColumn<Invoices, String> CategoryClm;

    @FXML
    private TextField txtFldId;

    @FXML
    private TextField txtFldName;

    @FXML
    private TextField txtFldQuantity;

    @FXML
    private TextField txtFldGame;

    @FXML
    private TextField txtFldTotal;

    @FXML
    private TextField txtFldCategory;

    @FXML
    private Button btnMain;

    @FXML
    private Button btnPrint;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();

        table.setOnMouseClicked(event -> {
            selectInvoices();
        });

        table.setOnKeyReleased(event -> {
            selectInvoices();
        });

    }


    private void selectInvoices() {
        Invoices selectedInvoices = table.getSelectionModel().getSelectedItem();
        if (selectedInvoices != null) {
            selectedRowIndex = table.getSelectionModel().getSelectedIndex();
            txtFldId.setText(selectedInvoices.getId());
            txtFldName.setText(selectedInvoices.getName());
            txtFldQuantity.setText(selectedInvoices.getQty());
            txtFldGame.setText(selectedInvoices.getGame());
            txtFldTotal.setText(selectedInvoices.getPrice());
            txtFldCategory.setText(selectedInvoices.getCategory());
        }
    }


    @FXML
    void mainInvoices(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameShop.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void PrintInvoice(ActionEvent event) {
        if (selectedRowIndex == -1) {
            System.out.println("No row selected.");
        } else {
            Invoices selectedInvoices = table.getItems().get(selectedRowIndex);
            String pdfFileName = "Invoices.pdf"; // The name of the output PDF file

            try {
                PDDocument document = new PDDocument();
                PDPage page = new PDPage();
                document.addPage(page);

                PDPageContentStream contentStream = new PDPageContentStream(document, page);

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);

                // Define initial Y position (start from the top of the page)
                float yPosition = page.getMediaBox().getHeight() - 50; // 50 points margin from the top

                // Add the header "############ INVOICES ############"
                contentStream.beginText();
                contentStream.newLineAtOffset( 130, yPosition);   // 220
                contentStream.showText("############ INVOICE ############");
                contentStream.endText();

                // Update the Y position for the next line
                yPosition -= 30; // Move up by 30 points

                // Add the headers "NAME", "QTY", "GAME", "TOTAL", "CATEGORY"
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, yPosition);
                contentStream.showText("NAME");
                contentStream.newLineAtOffset(90, 0);
                contentStream.showText("QTY");
                contentStream.newLineAtOffset(70, 0);
                contentStream.showText("GAME");
                contentStream.newLineAtOffset(70, 0);
                contentStream.showText("TOTAL");
                contentStream.newLineAtOffset(70, 0);
                contentStream.showText("CATEGORY");
                contentStream.endText();

                // Update the Y position for the next line
                yPosition -= 20; // Move up by 20 points

                // Add the row data
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, yPosition);
                contentStream.showText(selectedInvoices.getName());
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText(selectedInvoices.getQty());
                contentStream.newLineAtOffset(60, 0); //80
                contentStream.showText(selectedInvoices.getGame());
                contentStream.newLineAtOffset(80, 0); //100
                contentStream.showText(selectedInvoices.getPrice());
                contentStream.newLineAtOffset(80, 0); //160
                contentStream.showText(selectedInvoices.getCategory());
                contentStream.endText();

                contentStream.close();

                document.save(pdfFileName);
                document.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess !!");
                alert.setHeaderText("Printing in PDF file the selected invoice...");
                alert.setContentText("Printed the PDF Invoices.PDF with success.");
                alert.showAndWait();

                System.out.println("Selected row data saved to PDF: " + pdfFileName);
            } catch (IOException e) {
                System.err.println("Error saving row data to PDF: " + e.getMessage());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail !!");
                alert.setHeaderText("Something was wrong when printing in PDF file the selected invoice...");
                alert.setContentText("Fail to print the selected register.");
                alert.showAndWait();

            }
        }
    }



    public void table() { // This one is Using Inner Joins instead of CREATE a specific TABLE with the columns from others TABLES
        ObservableList<Invoices> invoicess = FXCollections.observableArrayList ();
        try {
            pst = con.prepareStatement ( "SELECT clients.id_client, clients.name_client, orders.qty, games.name_game, round(games.price * orders.qty, 2) AS TOTAL_PRICE, categories.name_category\n" +
                    "FROM orders\n" +
                    "INNER JOIN games ON orders.id_order = games.id_game\n" +
                    "INNER JOIN clients ON orders.id_order = clients.id_client\n" +
                    "INNER JOIN categories ON games.id_game = categories.id_category" );
            ResultSet rs = pst.executeQuery ();
            while (rs.next ()) {
                Invoices invoice = new Invoices ();
                invoice.setId ( rs.getString ( "clients.id_client" ) );
                invoice.setName ( rs.getString ( "clients.name_client" ) );
                invoice.setQty ( rs.getString ( "orders.qty" ) );
                invoice.setGame ( rs.getString ( "games.name_game" ) );
                invoice.setPrice ( rs.getString ( "TOTAL_Price" ) );
                invoice.setCategory ( rs.getString ( "categories.name_category" ) );
                invoicess.add ( invoice );
            }
        } catch (SQLException ex) {
            Logger.getLogger ( InvoicesController.class.getName () ).log ( Level.SEVERE, null, ex );
        }
        table.getSelectionModel ().setSelectionMode ( SelectionMode.SINGLE );
        ;
        table.setItems ( invoicess );


        IdClm.setCellValueFactory ( f -> f.getValue ().idProperty () );
        NameClm.setCellValueFactory ( f -> f.getValue ().nameProperty () );
        QuantityClm.setCellValueFactory ( f -> f.getValue ().qtyProperty () );
        GameClm.setCellValueFactory ( f -> f.getValue ().gameProperty () );
        TotalClm.setCellValueFactory ( f -> f.getValue ().priceProperty () );
        CategoryClm.setCellValueFactory ( f -> f.getValue ().categoryProperty () );

        table.getSelectionModel ().selectFirst ();
        selectInvoices ();
    }
    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gameShop", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InvoicesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

//    @FXML
//    void PrintInvoice(ActionEvent event) { // Prints the selected row in lines.
//        if (selectedRowIndex == -1) {
//            System.out.println("No row selected.");
//        } else {
//            Invoices selectedInvoices = table.getItems().get(selectedRowIndex);
//            String pdfFileName = "Invoices.pdf"; // The name of the output PDF file
//
//            try {
//                PDDocument document = new PDDocument();
//                PDPage page = new PDPage();
//                document.addPage(page);
//
//                PDPageContentStream contentStream = new PDPageContentStream(document, page);
//
//                contentStream.setFont(PDType1Font.HELVETICA, 12);
//
//                // Get the data from the selected row
//                String id       = selectedInvoices.getId ();
//                String name     = selectedInvoices.getName ();
//                String qty      = selectedInvoices.getQty ();
//                String game     = selectedInvoices.getGame ();
//                String total    = selectedInvoices.getPrice ();
//                String category = selectedInvoices.getCategory ();
//
//                // Define initial Y position (start from the top of the page)
//                float yPosition = page.getMediaBox().getHeight() - 50; // 50 points margin from the top
//
//                // Add the row data to the PDF content
//                contentStream.beginText();
//                contentStream.newLineAtOffset(100, yPosition);
//                contentStream.showText("############ INVOICES ############");
//
//                // Update the Y position for the next line
//                yPosition -= 15; // Move up by 15 points
//
//                contentStream.newLineAtOffset(0, -15);
//                contentStream.showText("Name: " + name);
//                yPosition -= 15;
//
//                contentStream.newLineAtOffset(0, -15);
//                contentStream.showText("QTY: " + qty);
//                yPosition -= 15;
//
//                contentStream.newLineAtOffset(0, -15);
//                contentStream.showText("GAME: " + game);
//                yPosition -= 15;
//
//                contentStream.newLineAtOffset(0, -15);
//                contentStream.showText("TOTAL: " + total);
//                yPosition -= 15;
//
//                contentStream.newLineAtOffset(0, -15);
//                contentStream.showText("CATEGORY: " + category);
//
//                contentStream.endText();
//                contentStream.close();
//
//                document.save(pdfFileName);
//                document.close();
//
//                System.out.println("Selected row data saved to PDF: " + pdfFileName);
//            } catch (IOException e) {
//                System.err.println("Error saving row data to PDF: " + e.getMessage());
//            }
//        }
//    }

        //    public void table() { // This one was CREATED a specific TABLE with the columns from others TABLES
//        ObservableList<Invoices> invoicess = FXCollections.observableArrayList();
//        try {
//            pst = con.prepareStatement("select id_invoice, invoice_name, qty_invoice, invoice_game_name, invoice_totalPrice, invoice_category from invoices");
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                Invoices invoice = new Invoices();
//                invoice.setId( rs.getString ( "id_invoice" ) );
//                invoice.setName( rs.getString ( "invoice_name" ) );
//                invoice.setQty( rs.getString ( "qty_invoice" ) );
//                invoice.setGame( rs.getString ( "invoice_game_name" ) );
//                invoice.setPrice( rs.getString ( "invoice_totalPrice" ) );
//                invoice.setCategory( rs.getString ( "invoice_category" ) );
//                invoicess.add(invoice);
//            }
//        }
//        catch (SQLException ex) {
//            Logger.getLogger(InvoicesController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        table.getSelectionModel().setSelectionMode( SelectionMode.SINGLE);;
//        table.setItems(invoicess);
//
//
//        IdClm.setCellValueFactory(f -> f.getValue().idProperty());
//        NameClm.setCellValueFactory(f -> f.getValue().nameProperty ());
//        QuantityClm.setCellValueFactory(f -> f.getValue ().qtyProperty ());
//        GameClm.setCellValueFactory(f -> f.getValue().gameProperty());
//        TotalClm.setCellValueFactory(f -> f.getValue().priceProperty ());
//        CategoryClm.setCellValueFactory(f -> f.getValue().categoryProperty ());
//
//        table.getSelectionModel().selectFirst();
//        selectInvoices();
//    }


