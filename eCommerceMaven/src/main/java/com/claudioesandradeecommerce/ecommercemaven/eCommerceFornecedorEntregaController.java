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

public class eCommerceFornecedorEntregaController {

    @FXML
    private TableView<FornecedorEntrega> table;

    @FXML
    private TableColumn<FornecedorEntrega, String> IdEntregaClm;

    @FXML
    private TableColumn<FornecedorEntrega, String> IdFornecedorClm;

    @FXML
    private TableColumn<FornecedorEntrega, String> DescricaoClm;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnMain;


    @FXML
    private TextField txtFldIdEntrega;

    @FXML
    private TextField txtFldIdFornecedor;

    @FXML
    private TextField txtFldDescricao;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectFornecedorEntrega();
        });

        table.setOnKeyReleased(event -> {
            selectFornecedorEntrega();
        });

        table.getSelectionModel().selectFirst();
        selectFornecedorEntrega();
    }

    private void selectFornecedorEntrega() {
        FornecedorEntrega   selectedFornecedorEntrega = table.getSelectionModel().getSelectedItem();
        if (selectedFornecedorEntrega  != null) {
            txtFldIdEntrega.setText(selectedFornecedorEntrega.getIdEntrega());
            txtFldIdFornecedor.setText(selectedFornecedorEntrega.getIdFornecedor());
            txtFldDescricao.setText(selectedFornecedorEntrega.getDescricao());
        }
    }

    @FXML
    void AddFornecedorEntrega(ActionEvent event) {

        String id            = txtFldIdEntrega.getText();
        String idFornecedor  = txtFldIdFornecedor.getText();
        String descricao     = txtFldDescricao.getText();

        try {
            pst = con.prepareStatement("insert into fornecedorEntrega(idFornecedorEntrega_entrega, idFornecedorEntrega_fornecedor, Descricao) values(?,?,?)");
            pst.setString(1, id);
            pst.setString(2, idFornecedor);
            pst.setString(3, descricao);
            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso !!");
                alert.setHeaderText("Registrando o Fornecedor Entrega");
                alert.setContentText("Registro adicionado com sucesso.");
                alert.showAndWait();

                table();
                txtFldIdEntrega.setText("");
                txtFldIdFornecedor.setText("");
                txtFldDescricao.setText("");

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Falha");
                alert.setHeaderText("Adicionando o Fornecedor Entrega");
                alert.setContentText("Falha na adição do Registro.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceFornecedorEntregaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void DelFornecedorEntrega(ActionEvent event) {

        int myIndex          = table.getSelectionModel().getSelectedIndex();
        int id               = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getIdEntrega() ));
        String idFornecedor  = txtFldIdFornecedor.getText();
        String descricao     = txtFldDescricao.getText();

        try
        {
            pst = con.prepareStatement("delete from fornecedorEntrega where idFornecedorEntrega_entrega = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Apagando o Fornecedor Entrega selecionado");
            alert.setHeaderText("Registro do Fornecedor Entrega");
            alert.setContentText("Apagado!");
            alert.showAndWait();

            table();
            txtFldIdEntrega.setText("");
            txtFldIdFornecedor.setText("");
            txtFldDescricao.setText("");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(eCommerceFornecedorEntregaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void UpdateFornecedorEntrega(ActionEvent event) {

        int myIndex          = table.getSelectionModel().getSelectedIndex();
        int id               = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getIdEntrega() ));
        String idFornecedor  = txtFldIdFornecedor.getText();
        String descricao     = txtFldDescricao.getText();

        try {
            pst = con.prepareStatement("update fornecedorEntrega  set idFornecedorEntrega_fornecedor = ?, Descricao = ? where idFornecedorEntrega_entrega = ?");
            pst.setString(1, idFornecedor);
            pst.setString(2, descricao);
            pst.setInt(3, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualizando informações de Fornecedor Produto");
            alert.setHeaderText("Registro do Fornecedor Entrega:");
            alert.setContentText("Registro Atualizado com sucesso!");
            alert.showAndWait();

            table();
            txtFldIdEntrega.setText("");
            txtFldIdFornecedor.setText("");
            txtFldDescricao.setText("");
            txtFldIdEntrega.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(eCommerceFornecedorEntregaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void mainFornecedorEntrega(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<FornecedorEntrega> fornecedorEntregas = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select idFornecedorEntrega_entrega, idFornecedorEntrega_fornecedor, Descricao from fornecedorEntrega");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                FornecedorEntrega    fornecedorEntrega   = new FornecedorEntrega();
                fornecedorEntrega.setIdEntrega(rs.getString("idFornecedorEntrega_entrega"));
                fornecedorEntrega.setIdFornecedor(rs.getString("idFornecedorEntrega_fornecedor"));
                fornecedorEntrega.setDescricao(rs.getString("Descricao"));
                fornecedorEntregas.add(fornecedorEntrega);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommerceFornecedorProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(fornecedorEntregas);

        // Assuming your FornecedorEntrega class has appropriate methods getIdEntregaProperty, getIdFornecedorProperty, and getDescricaoProperty
        IdEntregaClm.setCellValueFactory(f -> f.getValue().idFornecedor() );
        IdFornecedorClm.setCellValueFactory(f -> {
            String idFornecedorValue = f.getValue().getIdFornecedor();
            return new SimpleStringProperty(idFornecedorValue );
        });
        DescricaoClm.setCellValueFactory(f -> {
            String descricaoValue = f.getValue().getDescricao();
            return new SimpleStringProperty(descricaoValue );
        });
    }

    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MeuEsquemaEcommerce", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(eCommerceFornecedorEntregaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

