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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientsController {

    @FXML
    private TableView<Clients> table;

    @FXML
    private TableColumn<Clients, String> id_clientClm;

    @FXML
    private TableColumn<Clients, String> name_clientClm;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnMain;

    @FXML
    private TextField txtFldId_client;

    @FXML
    private TextField txtFldName_client;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectClients();
            });

        table.setOnKeyReleased(event -> {
            selectClients();
            });

        table.getSelectionModel().selectFirst();
            selectClients();
    }

    private void selectClients() {
        Clients   selectedClients = table.getSelectionModel().getSelectedItem();
        if (selectedClients  != null) {
            txtFldId_client.setText(selectedClients.getId_client() );
            txtFldName_client.setText(selectedClients.getName_client() );
        }
    }

    @FXML
    void AddClient(ActionEvent event) {
        String id_client     = txtFldId_client.getText();
        String name_client   = txtFldName_client.getText();

        try {
            pst = con.prepareStatement("insert into clients(id_client, name_client) values(?, ?)");
            pst.setString(1, id_client);
            pst.setString(2, name_client);

            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success !!");
                alert.setHeaderText("Registering the selected client");
                alert.setContentText("Register added with success.");
                alert.showAndWait();

                table();
                txtFldId_client.setText("");
                txtFldName_client.setText("");

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Adding the selected client...");
                    alert.setContentText("Fail to add the selected client.");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    @FXML
    void DelClient(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId_client() ));
        String name_client  = txtFldName_client.getText ();


        try
        {
            pst = con.prepareStatement("delete from clients where id_client = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleting the selected client");
            alert.setHeaderText("Register of client ");
            alert.setContentText("DELETED!");
            alert.showAndWait();

            table();
            txtFldId_client.setText("");
            txtFldName_client.setText("");

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void UpdateClient(ActionEvent event) {

        int myIndex          = table.getSelectionModel().getSelectedIndex();
        int id               = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId_client () ));
        String name_client   = txtFldName_client.getText ();

        try {
            pst = con.prepareStatement("update clients set name_client = ? where id_client = ?");
            pst.setString(1, name_client );
            pst.setString(2, String.valueOf ( id ) );
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Updating Clients");
            alert.setHeaderText("Client Register");
            alert.setContentText("Updated Register with success!");
            alert.showAndWait();

            table();
            txtFldId_client.setText("");
            txtFldName_client.setText("");
            txtFldId_client.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void mainClients(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameShop.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<Clients> clientss  =  FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select id_client, name_client from clients");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Clients clients = new Clients();
                clients.setId_client ( rs.getString("id_client"));
                clients.setName_client(rs.getString("name_client"));
                clientss.add(clients);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(clientss);

        id_clientClm.setCellValueFactory(f -> {
            String id_clientValue = f.getValue().getId_client();
            return new SimpleStringProperty ( id_clientValue );
        });
        name_clientClm.setCellValueFactory(f -> {
            String name_clientValue = f.getValue().getName_client();
            return new SimpleStringProperty(name_clientValue );
        });

    }


    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gameShop", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

