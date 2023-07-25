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

public class eCommerceFornecedorController {

    @FXML
    private TableView<Fornecedor> table;

    @FXML
    private TableColumn<Fornecedor, String> IdClm;

    @FXML
    private TableColumn<Fornecedor, String> RazaooSocialClm;

    @FXML
    private TableColumn<Fornecedor, String> CnpjClm;

    @FXML
    private TableColumn<Fornecedor, String> EnderecoClm;

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
            selectFornecedor();
        });

        table.setOnKeyReleased(event -> {
            selectFornecedor();
        });

        table.getSelectionModel().selectFirst();
            selectFornecedor();
    }

    private void selectFornecedor() {
        Fornecedor selectedFornecedor = table.getSelectionModel().getSelectedItem();
        if (selectedFornecedor  != null) {
            txtFldId.setText(selectedFornecedor.getId());
            txtFldRazaoSocial.setText(selectedFornecedor.getRazaoSocial());
            txtFldCnpj.setText(selectedFornecedor.getCnpj());
            txtFldEndereco.setText(selectedFornecedor.getEndereco());
        }
    }

    @FXML
    void AddFornecedor(ActionEvent event) {

        String id               = txtFldId.getText();
        String razaoSocial      = txtFldRazaoSocial.getText();;
        String cnpj             = txtFldCnpj.getText();
        String endereco         = txtFldEndereco.getText();

        try {
            pst = con.prepareStatement("insert into fornecedor(idFornecedor, RazaoSocial, IdentificacaoCNPJ, Endereco) values(?,?,?,?)");
            pst.setString(1, id);
            pst.setString(2, razaoSocial);
            pst.setString(3, cnpj);
            pst.setString(4, endereco);
            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso !!");
                alert.setHeaderText("Adicionando o Fornecedor");
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
                alert.setHeaderText("Adicionando o Fornecedor");
                alert.setContentText("Falha na adição do Registro.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void DelFornecedor(ActionEvent event) {

        int myIndex              = table.getSelectionModel().getSelectedIndex();
        int id                   = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String razaoSocial       = txtFldRazaoSocial.getText();
        String cnpj              = txtFldCnpj.getText();
        String endereco          = txtFldEndereco.getText();

        try
        {
            pst = con.prepareStatement("delete from fornecedor where idFornecedor = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Apagando o Fornecedor");
            alert.setHeaderText("Registro do Fornecedor");
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
            Logger.getLogger(eCommerceFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void UpdateFornecedor(ActionEvent event) {

        int myIndex           = table.getSelectionModel().getSelectedIndex();
        int id                = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String razaoSocial    = txtFldRazaoSocial.getText();
        String cnpj           = txtFldCnpj.getText();
        String endereco       = txtFldEndereco.getText();

        try {
            pst = con.prepareStatement("update fornecedor set RazaoSocial = ?, IdentificacaoCNPJ = ?, Endereco = ? where idFornecedor = ?");
            pst.setString(1, razaoSocial);
            pst.setString(2, cnpj);
            pst.setString(3, endereco);
            pst.setInt(4, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualizando o Fornecedor");
            alert.setHeaderText("Registro do Fornecedor");
            alert.setContentText("Atualizado com sucesso!");
            alert.showAndWait();

            table();
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void mainFornecedor(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<Fornecedor> fornecedors = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select idFornecedor, RazaoSocial, IdentificacaoCNPJ, Endereco from fornecedor");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Fornecedor fornecedor  = new Fornecedor();
                fornecedor.setId(rs.getString("idFornecedor"));
                fornecedor.setRazaoSocial(rs.getString("RazaoSocial"));
                fornecedor.setCnpj(rs.getString("IdentificacaoCNPJ"));
                fornecedor.setEndereco(rs.getString("Endereco"));
                fornecedors.add(fornecedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(fornecedors);

        // Assuming your Fornecedor class has appropriate methods getIdProperty, getRazaoSocial, and so on...
        IdClm.setCellValueFactory(f -> {
            return f.getValue().id();
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
            Logger.getLogger(eCommerceFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
