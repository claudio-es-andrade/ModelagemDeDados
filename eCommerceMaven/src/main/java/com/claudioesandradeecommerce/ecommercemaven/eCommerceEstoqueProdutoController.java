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

public class eCommerceEstoqueProdutoController {

    @FXML
    private TableView<EstoqueProduto> table;

    @FXML
    private TableColumn<EstoqueProduto, String> IdEstoqueClm;

    @FXML
    private TableColumn<EstoqueProduto, String> IdProdutoClm;

    @FXML
    private TableColumn<EstoqueProduto, String> QuantidadeClm;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnMain;

    @FXML
    private TextField txtFldIdEstoque;

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
            selectEstoqueProduto();
        });

        table.setOnKeyReleased(event -> {
            selectEstoqueProduto();
        });

        table.getSelectionModel().selectFirst();
        selectEstoqueProduto();
    }

    private void selectEstoqueProduto() {
        EstoqueProduto   selectedEstoqueProduto = table.getSelectionModel().getSelectedItem();
        if (selectedEstoqueProduto  != null) {
            txtFldIdEstoque.setText(selectedEstoqueProduto.getIdEstoque());
            txtFldIdProduto.setText(selectedEstoqueProduto.getIdProduto());
            txtFldQuantidade.setText(selectedEstoqueProduto.getQuantidade());
        }
    }

    @FXML
    void AddEstoqueProduto(ActionEvent event) {

        String idEstoque   = txtFldIdEstoque.getText();
        String idProduto   = txtFldIdProduto.getText();
        String quantidade  = txtFldQuantidade.getText();

        try {
            pst = con.prepareStatement("insert into estoqueProduto(idEstoqueProduto_estoque, idEstoqueProduto_produto, Quantidade) values(?,?,?)");
            pst.setString(1, idEstoque);
            pst.setString(2, idProduto);
            pst.setString(3, quantidade);
            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso !!");
                alert.setHeaderText("Registrando o Estoque Produto");
                alert.setContentText("Registro adicionado com sucesso.");
                alert.showAndWait();

                table();
                txtFldIdEstoque.setText("");
                txtFldIdProduto.setText("");
                txtFldQuantidade.setText("");

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Falha");
                alert.setHeaderText("Adicionando o Estoque Produto");
                alert.setContentText("Falha na adição do Registro.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceEstoqueProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void DelEstoqueProduto(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getIdEstoque() ));
        String idProduto  = txtFldIdProduto.getText();
        String quantidade = txtFldQuantidade.getText();

        try
        {
            pst = con.prepareStatement("delete from estoqueProduto where idEstoqueProduto_estoque = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Apagando o Produto Pedido selecionado");
            alert.setHeaderText("Registro do Produto Pedido");
            alert.setContentText("Apagado!");
            alert.showAndWait();

            table();
            txtFldIdEstoque.setText("");
            txtFldIdProduto.setText("");
            txtFldQuantidade.setText("");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(eCommerceEstoqueProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void UpdateEstoqueProduto(ActionEvent event) {

        int myIndex          = table.getSelectionModel().getSelectedIndex();
        int id               = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getIdEstoque() ));
        String idProduto     = txtFldIdProduto.getText();
        String Quantidade    = txtFldQuantidade.getText();

        try {
            pst = con.prepareStatement("update estoqueProduto  set idEstoqueProduto_produto = ?, Quantidade = ? where idEstoqueProduto_estoque = ?");
            pst.setString(1, idProduto);
            pst.setString(2, Quantidade);
            pst.setInt(3, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualizando informações de Estoque Produto");
            alert.setHeaderText("Registro do Estoque Produto");
            alert.setContentText("Registro Atualizado com sucesso!");
            alert.showAndWait();

            table();
            txtFldIdEstoque.setText("");
            txtFldIdProduto.setText("");
            txtFldQuantidade.setText("");
            txtFldIdEstoque.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(eCommerceEstoqueProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void mainEstoqueProduto(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<EstoqueProduto> estoqueProdutos  =  FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select idEstoqueProduto_estoque, idEstoqueProduto_produto, Quantidade from estoqueProduto");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                EstoqueProduto estoqueProduto = new EstoqueProduto();
                estoqueProduto.setIdEstoque(rs.getString("idEstoqueProduto_estoque"));
                estoqueProduto.setIdProduto(rs.getString("idEstoqueProduto_produto"));
                estoqueProduto.setQuantidade(rs.getString("Quantidade"));
                estoqueProdutos.add(estoqueProduto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceEstoqueProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(estoqueProdutos);

        // Assuming your ProdutpPedido class has appropriate methods getIdPedidoProperty, getIdProdutoProperty, and getQuantidadeProperty
        IdEstoqueClm.setCellValueFactory(f -> f.getValue().idEstoque() );
        IdProdutoClm.setCellValueFactory(f -> {
            String idPedidoValue = f.getValue().getIdProduto();
            return new SimpleStringProperty(idPedidoValue );
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
            Logger.getLogger(eCommerceEstoqueProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

