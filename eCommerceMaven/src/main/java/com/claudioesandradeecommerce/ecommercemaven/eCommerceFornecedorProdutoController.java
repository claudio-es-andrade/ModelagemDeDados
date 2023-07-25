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

public class eCommerceFornecedorProdutoController {

    @FXML
    private TableView<FornecedorProduto> table;
    @FXML
    private TableColumn<FornecedorProduto, String> IdFornecedorClm;

    @FXML
    private TableColumn<FornecedorProduto, String> IdProdutoClm;

    @FXML
    private TableColumn<FornecedorProduto, String> QuantidadeClm;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;


    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnMain;

    @FXML
    private TextField txtFldIdFornecedor;

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
            selectFornecedorProduto();
        });

        table.setOnKeyReleased(event -> {
            selectFornecedorProduto();
        });

        table.getSelectionModel().selectFirst();
        selectFornecedorProduto();
    }

    private void selectFornecedorProduto() {
        FornecedorProduto   selectedFornecedorProduto = table.getSelectionModel().getSelectedItem();
        if (selectedFornecedorProduto  != null) {
            txtFldIdFornecedor.setText(selectedFornecedorProduto.getIdFornecedor());
            txtFldIdProduto.setText(selectedFornecedorProduto.getIdProduto());
            txtFldQuantidade.setText(selectedFornecedorProduto.getQuantidade());
        }
    }

    @FXML
    void AddFornecedorProduto(ActionEvent event) {

        String id         = txtFldIdFornecedor.getText();
        String idProduto   = txtFldIdProduto.getText();
        String quantidade = txtFldQuantidade.getText();

        try {
            pst = con.prepareStatement("insert into fornecedorProduto(idFornecedorProduto_fornecedor, idFornecedorProduto_produto, Quantidade) values(?,?,?)");
            pst.setString(1, id);
            pst.setString(2, idProduto);
            pst.setString(3, quantidade);
            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso !!");
                alert.setHeaderText("Registrando o Fornecedor Produto");
                alert.setContentText("Registro adicionado com sucesso.");
                alert.showAndWait();

                table();
                txtFldIdFornecedor.setText("");
                txtFldIdProduto.setText("");
                txtFldQuantidade.setText("");

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Falha");
                alert.setHeaderText("Adicionando o Fornecedor Produto");
                alert.setContentText("Falha na adição do Registro.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceFornecedorProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void DelFornecedorProduto(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getIdFornecedor() ));
        String idProduto  = txtFldIdProduto.getText();
        String quantidade = txtFldQuantidade.getText();

        try
        {
            pst = con.prepareStatement("delete from fornecedorProduto where idFornecedorProduto_fornecedor = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Apagando o Fornecedor Produto selecionado");
            alert.setHeaderText("Registro do Fornecedor Produto");
            alert.setContentText("Apagado!");
            alert.showAndWait();

            table();
            txtFldIdFornecedor.setText("");
            txtFldIdProduto.setText("");
            txtFldQuantidade.setText("");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(eCommerceFornecedorProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void UpdateFornecedorProduto(ActionEvent event) {

        int myIndex          = table.getSelectionModel().getSelectedIndex();
        int id               = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getIdFornecedor() ));
        String idProduto      = txtFldIdProduto.getText();
        String Quantidade    = txtFldQuantidade.getText();

        try {
            pst = con.prepareStatement("update fornecedorProduto  set idFornecedorProduto_produto = ?, Quantidade = ? where idFornecedorProduto_fornecedor = ?");
            pst.setString(1, idProduto);
            pst.setString(2, Quantidade);
            pst.setInt(3, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualizando informações de Fornecedor Produto");
            alert.setHeaderText("Registro do Fornecedor Produto:");
            alert.setContentText("Registro Atualizado com sucesso!");
            alert.showAndWait();

            table();
            txtFldIdFornecedor.setText("");
            txtFldIdProduto.setText("");
            txtFldQuantidade.setText("");
            txtFldIdFornecedor.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(eCommerceFornecedorProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void mainFornecedorProduto(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<FornecedorProduto> fornecedorProdutos = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select idFornecedorProduto_fornecedor, idFornecedorProduto_produto, Quantidade from fornecedorProduto");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                FornecedorProduto  fornecedorProduto = new FornecedorProduto();
                fornecedorProduto.setIdFornecedor(rs.getString("idFornecedorProduto_fornecedor"));
                fornecedorProduto.setIdProduto(rs.getString("idFornecedorProduto_produto"));
                fornecedorProduto.setQuantidade(rs.getString("Quantidade"));
                fornecedorProdutos.add(fornecedorProduto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceFornecedorProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(fornecedorProdutos);

        // Assuming your FornecedorProduto class has appropriate methods getIdFornecedorProperty, getIdProdutoProperty, and getQuantidadeProperty
        IdFornecedorClm.setCellValueFactory(f -> f.getValue().idFornecedor() );
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
            Logger.getLogger(eCommerceFornecedorProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

