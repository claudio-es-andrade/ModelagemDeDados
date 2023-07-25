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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eCommerceEntregaController {

    @FXML
    private TableView<Entrega> table;

    @FXML
    private TableColumn<Entrega, String> IdClm;

    @FXML
    private TableColumn<Entrega, String> CodigoRastreioClm;

    @FXML
    private TableColumn<Entrega, String> SituacaoClm;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnMain;


    @FXML
    private TextField txtFldId;

    @FXML
    private TextField txtFldCodigoRastreio;

    @FXML
    private TextField txtFldSituacao;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectEntrega();
        });

        table.setOnKeyReleased(event -> {
            selectEntrega();
        });

        table.getSelectionModel().selectFirst();
        selectEntrega();
    }

    private void selectEntrega() {
        Entrega   selectEntrega = table.getSelectionModel().getSelectedItem();
        if (selectEntrega != null) {
            txtFldId.setText(selectEntrega.getId());
            txtFldCodigoRastreio.setText(selectEntrega.getCodigoRastreio());
            txtFldSituacao.setText(selectEntrega.getSituacao());
        }
    }

    @FXML
    void AddEntrega(ActionEvent event) {

        String id                 = txtFldId.getText();
        String codigoRastreio     = txtFldCodigoRastreio.getText();
        String situacao           = txtFldSituacao.getText();
        try {
            pst = con.prepareStatement("insert into entrega(idEntrega,codigoRastreio, situacao) values(?,?,?)");
            pst.setString(1, id);
            pst.setString(2, codigoRastreio);
            pst.setString(3, situacao);
            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso !!");
                alert.setHeaderText("Adicionando a ENTREGA");
                alert.setContentText("Registro adicionado com sucesso.");
                alert.showAndWait();

                table();
                txtFldId.setText("");
                txtFldCodigoRastreio.setText("");
                txtFldSituacao.setText("");
                txtFldId.requestFocus();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Falha");
                alert.setHeaderText("Adicionando a ENTREGA");
                alert.setContentText("Falha na adição do Registro.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceEntregaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void DelEntrega(ActionEvent event) {

        int myIndex                = table.getSelectionModel().getSelectedIndex();
        int id                     = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String codigoRastreio      = txtFldCodigoRastreio.getText();
        String situacao            = txtFldSituacao.getText();

        try
        {
            pst = con.prepareStatement("delete from entrega where idEntrega = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Apagando a ENTREGA");
            alert.setHeaderText("Registro da ENTREGA");
            alert.setContentText("Apagado!");

            alert.showAndWait();
            table();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(eCommerceEntregaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void UpdateEntrega(ActionEvent event) {

        int myIndex = table.getSelectionModel().getSelectedIndex();
        int id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String codigoRastreio     = txtFldCodigoRastreio.getText();
        String situacao           = txtFldSituacao.getText();

        try {
            pst = con.prepareStatement("update entrega set codigoRastreio = ?, situacao = ? where idEntrega = ?");
            pst.setString(1, codigoRastreio);
            pst.setString(2, situacao);
            pst.setInt(3, id); // Corrected parameter index from 4 to 3
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualizando a ENTREGA");
            alert.setHeaderText("Registro da ENTREGA");
            alert.setContentText("Atualizado com sucesso!");
            alert.showAndWait();

            table();
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceEntregaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void mainEntrega(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<Entrega> entregas  =  FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select idEntrega, codigoRastreio, situacao from entrega");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Entrega   entrega  = new Entrega();
                entrega.setId(rs.getString("idEntrega"));
                entrega.setCodigoRastreio(rs.getString("codigoRastreio"));
                entrega.setSituacao(rs.getString("situacao"));
                entregas.add(entrega);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceEntregaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(entregas);

        // Assuming your Entrega class has appropriate methods getIdProperty, getCodigoRastreioProperty, and getSituacaoProperty
        IdClm.setCellValueFactory(f -> f.getValue().idProperty());
        CodigoRastreioClm.setCellValueFactory(f -> {
            String codigoRastreioValue = f.getValue().getCodigoRastreio();
            return new SimpleStringProperty(codigoRastreioValue );
        });
        SituacaoClm.setCellValueFactory(f -> {
            String situacaoValue = f.getValue().getSituacao();
            return new SimpleStringProperty(situacaoValue );
        });
    }


    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MeuEsquemaEcommerce", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(eCommerceEntregaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

