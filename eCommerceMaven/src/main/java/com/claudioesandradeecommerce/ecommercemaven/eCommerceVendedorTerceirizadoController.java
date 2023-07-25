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

public class eCommerceVendedorTerceirizadoController {

    @FXML
    private TableView<VendedorTerceirizado> table;

    @FXML
    private TableColumn<VendedorTerceirizado, String> IdClm;

    @FXML
    private TableColumn<VendedorTerceirizado, String> RazaooSocialClm;

    @FXML
    private TableColumn<VendedorTerceirizado, String> CnpjClm;

    @FXML
    private TableColumn<VendedorTerceirizado, String> EnderecoClm;

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
    private TextField txtFldRazaoSocial;

    @FXML
    private TextField txtFldCnpj;

    @FXML
    private TextField txtFldEndereco;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectVendedorTerceirizado();
        });

        table.setOnKeyReleased(event -> {
            selectVendedorTerceirizado();
        });

        table.getSelectionModel().selectFirst();
        selectVendedorTerceirizado();
    }

    private void selectVendedorTerceirizado() {
        VendedorTerceirizado selectedVendedorTerceirizado = table.getSelectionModel().getSelectedItem();
        if (selectedVendedorTerceirizado  != null) {
            txtFldId.setText(selectedVendedorTerceirizado.getId());
            txtFldRazaoSocial.setText(selectedVendedorTerceirizado.getRazaoSocial());
            txtFldCnpj.setText(selectedVendedorTerceirizado.getCnpj());
            txtFldEndereco.setText(selectedVendedorTerceirizado.getEndereco());
        }
    }

    @FXML
    void AddVendedorTerceirizado(ActionEvent event) {

        String id               = txtFldId.getText();
        String razaoSocial      = txtFldRazaoSocial.getText();;
        String cnpj             = txtFldCnpj.getText();
        String endereco         = txtFldEndereco.getText();

        try {
            pst = con.prepareStatement("insert into vendedorTerceirizado(idVendedorTerceirizado, RazaoSocial, IdentificacaoCNPJ, Endereco) values(?,?,?,?)");
            pst.setString(1, id);
            pst.setString(2, razaoSocial);
            pst.setString(3, cnpj);
            pst.setString(4, endereco);
            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso !!");
                alert.setHeaderText("Adicionando o Vendedor Terceirizado");
                alert.setContentText("Registro adicionado com sucesso.");
                alert.showAndWait();

                table();
                txtFldId.setText("");
                txtFldRazaoSocial.setText("");
                txtFldCnpj.setText("");
                txtFldEndereco.setText("");
                txtFldId.requestFocus();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Falha");
                alert.setHeaderText("Adicionando o Vendedor Terceirizado");
                alert.setContentText("Falha na adição do Registro.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceVendedorTerceirizadoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void DelVendedorTerceirizado(ActionEvent event) {

        int myIndex              = table.getSelectionModel().getSelectedIndex();
        int id                   = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String razaoSocial       = txtFldRazaoSocial.getText();
        String cnpj              = txtFldCnpj.getText();
        String endereco          = txtFldEndereco.getText();

        try
        {
            pst = con.prepareStatement("delete from vendedorTerceirizado where idVendedorTerceirizado = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Apagando o Vendedor Terceirizado");
            alert.setHeaderText("Registro do Vendedor Terceirizado");
            alert.setContentText("Apagado!");

            alert.showAndWait();
            table();
            txtFldId.setText("");
            txtFldRazaoSocial.setText("");
            txtFldCnpj.setText("");
            txtFldEndereco.setText("");
            txtFldId.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(eCommerceVendedorTerceirizadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void UpdateVendedorTerceirizado(ActionEvent event) {

        int myIndex           = table.getSelectionModel().getSelectedIndex();
        int id                = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String razaoSocial    = txtFldRazaoSocial.getText();
        String cnpj           = txtFldCnpj.getText();
        String endereco       = txtFldEndereco.getText();

        try {
            pst = con.prepareStatement("update vendedorTerceirizado set RazaoSocial = ?, IdentificacaoCNPJ = ?, Endereco = ? where idVendedorTerceirizado = ?");
            pst.setString(1, razaoSocial);
            pst.setString(2, cnpj);
            pst.setString(3, endereco);
            pst.setInt(4, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualizando o Vendedor Terceirizado");
            alert.setHeaderText("Registro do Vendedor Terceirizado");
            alert.setContentText("Atualizado com sucesso!");
            alert.showAndWait();

            table();
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceVendedorTerceirizadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void mainVendedorTerceirizado(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<VendedorTerceirizado> vendedorTerceirizados = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select idVendedorTerceirizado, RazaoSocial, IdentificacaoCNPJ, Endereco from vendedorTerceirizado");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                VendedorTerceirizado vendedorTerceirizado  = new VendedorTerceirizado();
                vendedorTerceirizado.setId(rs.getString("idVendedorTerceirizado"));
                vendedorTerceirizado.setRazaoSocial(rs.getString("RazaoSocial"));
                vendedorTerceirizado.setCnpj(rs.getString("IdentificacaoCNPJ"));
                vendedorTerceirizado.setEndereco(rs.getString("Endereco"));

                vendedorTerceirizados.add(vendedorTerceirizado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceVendedorTerceirizadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(vendedorTerceirizados);

        // Assuming your Vendedor Terceirizado class has appropriate methods getIdProperty, getRazaoSocial, and so on...
        IdClm.setCellValueFactory(f -> {
            return f.getValue().idProperty();
        });

        RazaooSocialClm.setCellValueFactory(f -> {
            String RazaoSocialValue = f.getValue().getRazaoSocial();
            return new SimpleStringProperty(RazaoSocialValue);
        });
        CnpjClm.setCellValueFactory(f -> {
            String CnpjValue = f.getValue().getCnpj();
            return new SimpleStringProperty(CnpjValue);
        });
        EnderecoClm.setCellValueFactory(f -> {
            String EnderecoValue = f.getValue().getEndereco();
            return new SimpleStringProperty(EnderecoValue);
        });
    }

    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MeuEsquemaEcommerce", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(eCommerceVendedorTerceirizadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

