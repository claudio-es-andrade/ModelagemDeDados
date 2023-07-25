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

public class eCommerceEstoqueController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnMain;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Estoque> table;
    @FXML
    private TableColumn<Estoque, String > IdClm;

    @FXML
    private TableColumn<Estoque, String > EnderecoClm;

    @FXML
    private TableColumn<Estoque, String > DescricaoClm;

    @FXML
    private TableColumn<Estoque, String > QuantidadeClm;

    @FXML
    private TextField txtFldId;

    @FXML
    private TextField txtFldEndereco;

    @FXML
    private TextField txtFldDescricao;

    @FXML
    private TextField txtFldQuantidade;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectEstoque();
        });

        table.setOnKeyReleased(event -> {
            selectEstoque();
        });

        table.getSelectionModel().selectFirst();
        selectEstoque();
    }


    private void selectEstoque() {
        Estoque selectedEstoque = (Estoque) table.getSelectionModel().getSelectedItem();
        if (selectedEstoque != null) {
            txtFldId.setText(selectedEstoque.getId());
            txtFldEndereco.setText(selectedEstoque.getEndereco());
            txtFldDescricao.setText(selectedEstoque.getDescricao());
            txtFldQuantidade.setText(selectedEstoque.getQuantidade());
        }
    }

    @FXML
    void AddEstoque(ActionEvent event) {

        String id         = txtFldId.getText();
        String endereco   = txtFldEndereco.getText();
        String descricao  = txtFldDescricao.getText();
        String quantidade = txtFldQuantidade.getText();
        try {
            pst = con.prepareStatement("insert into estoque(idEstoque, Endereco, Descricao, Quantidade) values(?,?,?,?)");
            pst.setString(1, id);
            pst.setString(2, endereco);
            pst.setString(3, descricao);
            pst.setString(4, quantidade);
            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso !!");
                alert.setHeaderText("Registrando o Estoque");
                alert.setContentText("Registro adicionado com sucesso.");
                alert.showAndWait();

                table();
                txtFldId.setText("");
                txtFldEndereco.setText("");
                txtFldDescricao.setText("");
                txtFldQuantidade.setText("");
                txtFldId.requestFocus();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Falha");
                alert.setHeaderText("Adicionando o Estoque");
                alert.setContentText("Falha na adição do Registro.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceEstoqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void DelEstoque(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String endereco   = txtFldEndereco.getText();
        String descricao  = txtFldDescricao.getText();
        String quantidade = txtFldQuantidade.getText();

        try
        {
            pst = con.prepareStatement("delete from estoque where idEstoque = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Apagando o estoque selecionado");
            alert.setHeaderText("Registro do Estoque");
            alert.setContentText("Apagado!");
            alert.showAndWait();

            table();
            txtFldId.setText("");
            txtFldEndereco.setText("");
            txtFldDescricao.setText("");
            txtFldQuantidade.setText("");
            txtFldId.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(eCommerceEstoqueController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void UpdateEstoque(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String endereco   = txtFldEndereco.getText();
        String descricao  = txtFldDescricao.getText();
        String quantidade = txtFldQuantidade.getText();

        try {
            pst = con.prepareStatement("update estoque set Endereco = ?, Descricao = ?, Quantidade = ? where idEstoque = ?");
            pst.setString(1, endereco);
            pst.setString(2, descricao);
            pst.setString(3, quantidade);
            pst.setInt(4, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualizando informações de Estoque");
            alert.setHeaderText("Registro do Estoque:");
            alert.setContentText("Registro Atualizado com sucesso!");
            alert.showAndWait();

            table();
            txtFldId.setText("");
            txtFldEndereco.setText("");
            txtFldDescricao.setText("");
            txtFldQuantidade.setText("");
            txtFldId.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(eCommerceEstoqueController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void mainEstoque(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<Estoque> estoques = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select idEstoque, Endereco, Descricao, Quantidade from estoque");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Estoque estoque    = new Estoque();
                estoque.setId(rs.getString("idEstoque"));
                estoque.setEndereco(rs.getString("Endereco"));
                estoque.setDescricao(rs.getString("Descricao"));
                estoque.setQuantidade(rs.getString("Quantidade"));
                estoques.add(estoque);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceEstoqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(estoques);

        // Assuming your Estoque class has appropriate methods getIdProperty, getEndereco, and so on...
        IdClm.setCellValueFactory(f -> f.getValue().idProperty());

        EnderecoClm.setCellValueFactory(f -> {
            String EnderecoValue = f.getValue().getEndereco();
            return new SimpleStringProperty(EnderecoValue);
        });
        DescricaoClm.setCellValueFactory(f -> {
            String DescricaoValue = f.getValue().getDescricao();
            return new SimpleStringProperty(DescricaoValue);
        });
        QuantidadeClm.setCellValueFactory(f -> {
            String QuantidadeValue = f.getValue().getQuantidade();
            return new SimpleStringProperty(QuantidadeValue);
        });
    }

    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MeuEsquemaEcommerce", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(eCommerceEstoqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
