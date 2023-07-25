package com.claudioesandradeecommerce.ecommercemaven;

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

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class eCommerceNotaController {

    private int selectedRowIndex = -1;

    @FXML
    private TableView<Nota> table;

    @FXML
    private TableColumn<Nota, String> NomeClm;

    @FXML
    private TableColumn<Nota, String> CpfClm;

    @FXML
    private TableColumn<Nota, String> CnpjClm;

    @FXML
    private TableColumn<Nota, String> CategoriaClm;

    @FXML
    private TableColumn<Nota, String> SituacaoClm;

    @FXML
    private TableColumn<Nota, String> ValorClm;

    @FXML
    private TableColumn<Nota, String> FreteClm;


    @FXML
    private Button btnPrint;

    @FXML
    private Button btnMain;

    @FXML
    private TextField txtFldNome;

    @FXML
    private TextField txtFldCpf;

    @FXML
    private TextField txtFldCnpj;

    @FXML
    private TextField txtFldCategoria;

    @FXML
    private TextField txtFldSituacao;

    @FXML
    private TextField txtFldValor;

    @FXML
    private TextField txtFldFrete;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();

        table.setOnMouseClicked(event -> {
            selectNota();
        });

        table.setOnKeyReleased(event -> {
            selectNota();
        });
        
        // table.getSelectionModel().selectFirst();
        //selectNota();
    }


    private void selectNota() {

        Nota selectedNota = (Nota) table.getSelectionModel ().getSelectedItem ();
        if (selectedNota != null) {
            selectedRowIndex = table.getSelectionModel ().getSelectedIndex ();
            txtFldNome.setText ( selectedNota.getNome () );
            txtFldCpf.setText ( selectedNota.getCpf () );
            txtFldCnpj.setText ( selectedNota.getCnpj () );
            txtFldCategoria.setText ( selectedNota.getCategoria () );
            txtFldSituacao.setText ( selectedNota.getSituacao () );
            txtFldValor.setText ( selectedNota.getValor () );
            txtFldFrete.setText ( selectedNota.getFrete () );
        }
    }

    @FXML
    void mainNota(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void PrintNota(ActionEvent event) {

        if (selectedRowIndex == -1) {
            System.out.println("No row selected.");
        } else {
            Nota selectedNota = table.getItems().get(selectedRowIndex);
            String pdfFileName = "NOTA_FISCAL.pdf"; // The name of the output PDF file

            try {
                PDDocument document = new PDDocument();
                PDPage page = new PDPage();
                document.addPage(page);

                PDPageContentStream contentStream = new PDPageContentStream(document, page);

                // Use Arial font instead of Helvetica
                contentStream.setFont(PDType1Font.HELVETICA, 12);

                // Get the data from the selected row
                String nome = selectedNota.getNome();
                String cpf = selectedNota.getCpf();
                String cnpj = selectedNota.getCnpj();
                String categoria = selectedNota.getCategoria();
                String situacao = selectedNota.getSituacao();
                String valor = selectedNota.getValor();
                String frete = selectedNota.getFrete();

                // Define initial Y position (start from the top of the page)
                float yPosition = page.getMediaBox().getHeight() - 50; // 50 points margin from the top

                // Add the row data to the PDF content
                contentStream.beginText();
                contentStream.newLineAtOffset(100, yPosition);
                contentStream.showText("### Nota Fiscal ###");

                // Update the Y position for the next line
                yPosition -= 15; // Move up by 15 points

                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Nome: " + nome);
                yPosition -= 15;

                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("CPF: " + cpf);
                yPosition -= 15;

                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("CNPJ: " + cnpj);
                yPosition -= 15;

                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Categoria: " + categoria);
                yPosition -= 15;

                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Situação: " + situacao);
                yPosition -= 15;

                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Valor: " + valor);
                yPosition -= 15;

                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Frete: " + frete);
                contentStream.endText();
                contentStream.close();

                document.save(pdfFileName);
                document.close();

                System.out.println("Selected row data saved to PDF: " + pdfFileName);
            } catch (IOException e) {
                System.err.println("Error saving row data to PDF: " + e.getMessage());
            }
        }
    }

    public void table() {
        ObservableList<Nota> notas = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select Nome, IdentificacaoCPF, IdentificacaoCNPJ, Categoria, situacao, Valor, frete from nota");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Nota nota = new Nota();
                nota.setNome ( rs.getString ( "Nome" ) );
                nota.setCpf ( rs.getString ( "IdentificacaoCPF" ) );
                nota.setCnpj ( rs.getString ( "IdentificacaoCNPJ" ) );
                nota.setCategoria ( rs.getString ( "Categoria" ) );
                nota.setSituacao ( rs.getString ( "situacao" ) );
                nota.setValor ( rs.getString ( "Valor" ) );
                nota.setFrete ( rs.getString ( "Frete" ) );
                notas.add ( nota );
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(eCommerceNotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.getSelectionModel().setSelectionMode( SelectionMode.SINGLE);;
        table.setItems ( notas );


        NomeClm.setCellValueFactory(f -> f.getValue().nomeProperty());
        CpfClm.setCellValueFactory(f -> f.getValue().cpfProperty());
        CnpjClm.setCellValueFactory(f -> f.getValue().cnpjProperty());
        CategoriaClm.setCellValueFactory(f -> f.getValue().categoriaProperty());
        SituacaoClm.setCellValueFactory(f -> f.getValue().situacaoProperty());
        ValorClm.setCellValueFactory(f -> f.getValue().valorProperty());
        FreteClm.setCellValueFactory(f -> f.getValue().freteProperty());

        table.getSelectionModel().selectFirst();
        selectNota();
    }

    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MeuEsquemaEcommerce", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(eCommerceNotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

