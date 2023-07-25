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

public class eCommerceProdutoController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnMain;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Produto> table;
    @FXML
    private TableColumn<Produto, String > IdClm;

    @FXML
    private TableColumn<Produto, String > CategoriaClm;

    @FXML
    private TableColumn<Produto, String > DescricaoClm;

    @FXML
    private TableColumn<Produto, String > ValorClm;

    @FXML
    private TextField txtFldId;

    @FXML
    private TextField txtFldCategoria;

    @FXML
    private TextField txtFldDescricao;

    @FXML
    private TextField txtFldValor;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectProduto();
        });

        table.setOnKeyReleased(event -> {
            selectProduto();
        });

        table.getSelectionModel().selectFirst();
        selectProduto();
    }


    private void selectProduto() {
        Produto selectedProduto = (Produto) table.getSelectionModel().getSelectedItem();
        if (selectedProduto != null) {
            txtFldId.setText(selectedProduto.getId());
            txtFldCategoria.setText(selectedProduto.getCategoria());
            txtFldDescricao.setText(selectedProduto.getDescricao());
            txtFldValor.setText(selectedProduto.getValor());
        }
    }

    @FXML
    void AddProduto(ActionEvent event) {

        String id         = txtFldId.getText();
        String categoria  = txtFldCategoria.getText();
        String descricao  = txtFldDescricao.getText();
        String valor      = txtFldValor.getText();
        try {
            pst = con.prepareStatement("insert into produto(idProduto, Categoria, Descricao, Valor) values(?,?,?,?)");
            pst.setString(1, id);
            pst.setString(2, categoria);
            pst.setString(3, descricao);
            pst.setString(4, valor);
            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso !!");
                alert.setHeaderText("Registrando o produto");
                alert.setContentText("Registro adicionado com sucesso.");
                alert.showAndWait();

                table();
                txtFldId.setText("");
                txtFldCategoria.setText("");
                txtFldDescricao.setText("");
                txtFldValor.setText("");
                txtFldId.requestFocus();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Falha");
                alert.setHeaderText("Adicionando o produto");
                alert.setContentText("Falha na adição do Registro.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void DelProduto(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String categoria  = txtFldCategoria.getText();;
        String descricao  = txtFldDescricao.getText();
        String valor      = txtFldValor.getText();

        try
        {
            pst = con.prepareStatement("delete from produto where idProduto = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Apagando o produto selecionado");
            alert.setHeaderText("Registro de Produto");
            alert.setContentText("Apagado!");
            alert.showAndWait();

            table();
            txtFldId.setText("");
            txtFldCategoria.setText("");
            txtFldDescricao.setText("");
            txtFldValor.setText("");
            txtFldId.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(eCommerceProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void UpdateProduto(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String categoria  = txtFldCategoria.getText();
        String descricao  = txtFldDescricao.getText();
        String valor      = txtFldValor.getText();

        try {
            pst = con.prepareStatement("update produto set Categoria = ?, Descricao = ?, Valor = ? where idProduto = ?");
            pst.setString(1, categoria);
            pst.setString(2, descricao);
            pst.setString(3, valor);
            pst.setInt(4, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualizando informações do Produto");
            alert.setHeaderText("Registro do Produto:");
            alert.setContentText("Registro Atualizado com sucesso!");
            alert.showAndWait();

            table();
            txtFldId.setText("");
            txtFldCategoria.setText("");
            txtFldDescricao.setText("");
            txtFldValor.setText("");
            txtFldId.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(eCommerceProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void mainProduto(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select idProduto, Categoria, Descricao, Valor from produto");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produto produto    = new Produto();
                produto.setId(rs.getString("idProduto"));
                produto.setCategoria(rs.getString("Categoria"));
                produto.setDescricao(rs.getString("Descricao"));
                produto.setValor(rs.getString("Valor"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(produtos);

        // Assuming your Pedido class has appropriate methods getIdProperty, getSituacao, and so on...
        IdClm.setCellValueFactory(f -> f.getValue().idProperty());

        CategoriaClm.setCellValueFactory(f -> {
            String CategoriaValue = f.getValue().getCategoria();
            return new SimpleStringProperty(CategoriaValue);
        });
        DescricaoClm.setCellValueFactory(f -> {
            String DescricaoValue = f.getValue().getDescricao();
            return new SimpleStringProperty(DescricaoValue);
        });
        ValorClm.setCellValueFactory(f -> {
            String ValorValue = f.getValue().getValor();
            return new SimpleStringProperty(ValorValue);
        });
    }

    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MeuEsquemaEcommerce", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(eCommerceProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
