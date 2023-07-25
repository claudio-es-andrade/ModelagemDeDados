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

public class eCommerceProdutoPedidoController {

    @FXML
    private TableView<ProdutoPedido> table;

    @FXML
    private TableColumn<ProdutoPedido, String> IdProdutoClm;

    @FXML
    private TableColumn<ProdutoPedido, String> IdPedidoClm;

    @FXML
    private TableColumn<ProdutoPedido, String> QuantidadeClm;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnMain;

    @FXML
    private TextField txtFldIdProduto;

    @FXML
    private TextField txtFldIdPedido;

    @FXML
    private TextField txtFldQuantidade;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectProdutoPedido();
        });

        table.setOnKeyReleased(event -> {
            selectProdutoPedido();
        });

        table.getSelectionModel().selectFirst();
        selectProdutoPedido();
    }

    private void selectProdutoPedido() {
        ProdutoPedido   selectedProdutoPedido = table.getSelectionModel().getSelectedItem();
        if (selectedProdutoPedido  != null) {
            txtFldIdProduto.setText(selectedProdutoPedido.getIdProduto());
            txtFldIdPedido.setText(selectedProdutoPedido.getIdPedido());
            txtFldQuantidade.setText(selectedProdutoPedido.getQuantidade());
        }
    }

    @FXML
    void AddProdutoPedido(ActionEvent event) {

        String id         = txtFldIdProduto.getText();
        String idPedido   = txtFldIdPedido.getText();
        String quantidade = txtFldQuantidade.getText();

        try {
            pst = con.prepareStatement("insert into produtoPedido(idProdutoPedido_produto, idProdutoPedido_pedido, Quantidade) values(?,?,?)");
            pst.setString(1, id);
            pst.setString(2, idPedido);
            pst.setString(3, quantidade);
            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso !!");
                alert.setHeaderText("Registrando o Produto Pedido");
                alert.setContentText("Registro adicionado com sucesso.");
                alert.showAndWait();

                table();
                txtFldIdProduto.setText("");
                txtFldIdPedido.setText("");
                txtFldQuantidade.setText("");

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Falha");
                alert.setHeaderText("Adicionando o Produto Pedido");
                alert.setContentText("Falha na adição do Registro.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceProdutoPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void DelProdutoPedido(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getIdProduto()));
        String idPedido   = txtFldIdPedido.getText();
        String quantidade = txtFldQuantidade.getText();

        try
        {
            pst = con.prepareStatement("delete from produtoPedido where idProdutoPedido_produto = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Apagando o Produto Pedido selecionado");
            alert.setHeaderText("Registro do Produto Pedido");
            alert.setContentText("Apagado!");
            alert.showAndWait();

            table();
            txtFldIdProduto.setText("");
            txtFldIdPedido.setText("");
            txtFldQuantidade.setText("");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(eCommerceProdutoPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void UpdateProdutoPedido(ActionEvent event) {

        int myIndex          = table.getSelectionModel().getSelectedIndex();
        int id               = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getIdProduto()));
        String idPedido      = txtFldIdPedido.getText();
        String Quantidade    = txtFldQuantidade.getText();

        try {
            pst = con.prepareStatement("update produtoPedido  set idProdutoPedido_pedido = ?, Quantidade = ? where idProdutoPedido_produto = ?");
            pst.setString(1, idPedido);
            pst.setString(2, Quantidade);
            pst.setInt(3, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualizando informações de Produto Pedido");
            alert.setHeaderText("Registro do Produto Pedido:");
            alert.setContentText("Registro Atualizado com sucesso!");
            alert.showAndWait();

            table();
            txtFldIdProduto.setText("");
            txtFldIdPedido.setText("");
            txtFldQuantidade.setText("");
            txtFldIdProduto.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(eCommerceProdutoPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void mainProdutoPedido(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<ProdutoPedido> produtoPedidos = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select idProdutoPedido_produto, idProdutoPedido_pedido, Quantidade from produtoPedido");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ProdutoPedido  produtoPedido = new ProdutoPedido();
                produtoPedido.setIdProduto(rs.getString("idProdutoPedido_produto"));
                produtoPedido.setIdPedido(rs.getString("idProdutoPedido_pedido"));
                produtoPedido.setQuantidade(rs.getString("Quantidade"));
                produtoPedidos.add(produtoPedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceProdutoPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(produtoPedidos);

        // Assuming your ProdutpPedido class has appropriate methods getIdPedidoProperty, getIdProdutoProperty, and getQuantidadeProperty
        IdProdutoClm.setCellValueFactory(f -> f.getValue().idProdutoProperty());
        IdPedidoClm.setCellValueFactory(f -> {
            String idPedidoValue = f.getValue().getIdPedido();
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
            Logger.getLogger(eCommerceProdutoPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

