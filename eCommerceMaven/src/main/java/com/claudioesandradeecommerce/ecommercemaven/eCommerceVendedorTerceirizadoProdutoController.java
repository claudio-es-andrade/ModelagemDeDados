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

public class eCommerceVendedorTerceirizadoProdutoController {

    @FXML
    private TableView<VendedorTerceirizadoProduto> table;

    @FXML
    private TableColumn<VendedorTerceirizadoProduto, String> IdVendedorClm;

    @FXML
    private TableColumn<VendedorTerceirizadoProduto, String> IdProdutoClm;

    @FXML
    private TableColumn<VendedorTerceirizadoProduto, String> QuantidadeClm;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;


    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnMain;

    @FXML
    private TextField txtFldIdVendedor;

    @FXML
    private TextField txtFldIdProduto;

    @FXML
    private TextField txtFldQuantidade;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectVendedorTerceirizadoProduto();
        });

        table.setOnKeyReleased(event -> {
            selectVendedorTerceirizadoProduto();
        });

        table.getSelectionModel().selectFirst();
        selectVendedorTerceirizadoProduto();
    }

    private void selectVendedorTerceirizadoProduto() {
        VendedorTerceirizadoProduto   selectedVendedorTerceirizadoProduto = table.getSelectionModel().getSelectedItem();
        if (selectedVendedorTerceirizadoProduto  != null) {
            txtFldIdVendedor.setText(selectedVendedorTerceirizadoProduto.getIdVendedor());
            txtFldIdProduto.setText(selectedVendedorTerceirizadoProduto.getIdProduto());
            txtFldQuantidade.setText(selectedVendedorTerceirizadoProduto.getQuantidade());
        }
    }

    @FXML
    void AddVendedorTerceirizadoProduto(ActionEvent event) {

        String id         = txtFldIdVendedor.getText();
        String idProduto   = txtFldIdProduto.getText();
        String quantidade = txtFldQuantidade.getText();

        try {
            pst = con.prepareStatement("insert into vendedorTerceirizadoProduto(idVendedorTerceirizadoProduto_vendedorTerceirizado, idVendedorTerceirizadoProduto_produto, Quantidade) values(?,?,?)");
            pst.setString(1, id);
            pst.setString(2, idProduto);
            pst.setString(3, quantidade);
            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso !!");
                alert.setHeaderText("Registrando o Vendedor Terceirizado Produto");
                alert.setContentText("Registro adicionado com sucesso.");
                alert.showAndWait();

                table();
                txtFldIdVendedor.setText("");
                txtFldIdProduto.setText("");
                txtFldQuantidade.setText("");

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Falha");
                alert.setHeaderText("Adicionando o Vendedor Terceirizado Produto");
                alert.setContentText("Falha na adição do Registro.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceVendedorTerceirizadoProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void DelVendedorTerceirizadoProduto(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getIdVendedor() ));
        String idProduto  = txtFldIdProduto.getText();
        String quantidade = txtFldQuantidade.getText();

        try
        {
            pst = con.prepareStatement("delete from vendedorTerceirizadoProduto where idVendedorTerceirizadoProduto_vendedorTerceirizado = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Apagando o Vendedor Terceirizado Produto selecionado");
            alert.setHeaderText("Registro do Vendedor Terceirizado Produto");
            alert.setContentText("Apagado!");
            alert.showAndWait();

            table();
            txtFldIdVendedor.setText("");
            txtFldIdProduto.setText("");
            txtFldQuantidade.setText("");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(eCommerceVendedorTerceirizadoProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void UpdateVendedorTerceirizadoProduto(ActionEvent event) {

        int myIndex          = table.getSelectionModel().getSelectedIndex();
        int id               = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getIdVendedor() ));
        String idProduto      = txtFldIdProduto.getText();
        String Quantidade    = txtFldQuantidade.getText();

        try {
            pst = con.prepareStatement("update vendedorTerceirizadoProduto  set idVendedorTerceirizadoProduto_produto = ?, Quantidade = ? where idVendedorTerceirizadoProduto_vendedorTerceirizado = ?");
            pst.setString(1, idProduto);
            pst.setString(2, Quantidade);
            pst.setInt(3, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualizando informações de Vendedor Terceirizado Produto");
            alert.setHeaderText("Registro do Vendedor Terceirizado Produto:");
            alert.setContentText("Registro Atualizado com sucesso!");
            alert.showAndWait();

            table();
            txtFldIdVendedor.setText("");
            txtFldIdProduto.setText("");
            txtFldQuantidade.setText("");
            txtFldIdVendedor.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(eCommerceVendedorTerceirizadoProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void mainVendedorTerceirizadoProduto(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<VendedorTerceirizadoProduto> vendedorTerceirizadoProdutos = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select idVendedorTerceirizadoProduto_vendedorTerceirizado, idVendedorTerceirizadoProduto_produto, Quantidade from vendedorTerceirizadoProduto");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                VendedorTerceirizadoProduto  vendedorTerceirizadoProduto = new VendedorTerceirizadoProduto();

                vendedorTerceirizadoProduto.setIdVendedor(rs.getString("idVendedorTerceirizadoProduto_vendedorTerceirizado"));
                vendedorTerceirizadoProduto.setIdProduto(rs.getString("idVendedorTerceirizadoProduto_produto"));
                vendedorTerceirizadoProduto.setQuantidade(rs.getString("Quantidade"));

                vendedorTerceirizadoProdutos.add(vendedorTerceirizadoProduto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceVendedorTerceirizadoProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(vendedorTerceirizadoProdutos);

        // Assuming your Vendedor Terceirizado Produto class has appropriate methods getIdVendedorProperty, getIdProdutoProperty, and getQuantidadeProperty
        IdVendedorClm.setCellValueFactory(f -> f.getValue().idVendedorProperty() );
        IdProdutoClm.setCellValueFactory(f -> {
            String idProdutoValue = f.getValue().getIdProduto();
            return new SimpleStringProperty(idProdutoValue );
        });
        QuantidadeClm.setCellValueFactory(f -> {
            String QuantidadeValue = f.getValue().getQuantidade();
            return new SimpleStringProperty(QuantidadeValue );
        });
    }


    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MeuEsquemaEcommerce", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(eCommerceVendedorTerceirizadoProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

