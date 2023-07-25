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

public class eCommercePedidoController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnMain;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Pedido> table;
    @FXML
    private TableColumn<Pedido, String > IdClm;

    @FXML
    private TableColumn<Pedido, String > SituacaoClm;

    @FXML
    private TableColumn<Pedido, String > DescricaoClm;

    @FXML
    private TableColumn<Pedido, String > FreteClm;

    @FXML
    private TextField txtFldId;

    @FXML
    private TextField txtFldSituacao;

    @FXML
    private TextField txtFldDescricao;

    @FXML
    private TextField txtFldFrete;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectPedido();
        });

        table.setOnKeyReleased(event -> {
            selectPedido();
        });

        table.getSelectionModel().selectFirst();
        selectPedido();
    }


    private void selectPedido() {
        Pedido selectedPedido = (Pedido) table.getSelectionModel().getSelectedItem();
        if (selectedPedido != null) {
            txtFldId.setText(selectedPedido.getId());
            txtFldSituacao.setText(selectedPedido.getSituacao());
            txtFldDescricao.setText(selectedPedido.getDescricao());
            txtFldFrete.setText(selectedPedido.getFrete());
        }
    }

    @FXML
    void AddPedido(ActionEvent event) {

        String id         = txtFldId.getText();
        String situacao   = txtFldSituacao.getText();
        String descricao  = txtFldDescricao.getText();
        String frete      = txtFldFrete.getText();
        try {
            pst = con.prepareStatement("insert into pedido(idPedido, situacao, descricao, frete) values(?,?,?,?)");
            pst.setString(1, id);
            pst.setString(2, situacao);
            pst.setString(3, descricao);
            pst.setString(4, frete);
            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso !!");
                alert.setHeaderText("Registrando o pedido");
                alert.setContentText("Registro adicionado com sucesso.");
                alert.showAndWait();

                table();
                txtFldId.setText("");
                txtFldSituacao.setText("");
                txtFldDescricao.setText("");
                txtFldFrete.setText("");
                txtFldId.requestFocus();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Falha");
                alert.setHeaderText("Adicionando o pedido");
                alert.setContentText("Falha na adição do Registro.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommercePedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void DelPedido(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String situacao   = txtFldSituacao.getText();
        String descricao  = txtFldDescricao.getText();
        String frete      = txtFldFrete.getText();

        try
        {
            pst = con.prepareStatement("delete from pedido where idPedido = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Apagando o pedido selecionado");
            alert.setHeaderText("Registro de Pedido");
            alert.setContentText("Apagado!");
            alert.showAndWait();

            table();
            txtFldId.setText("");
            txtFldSituacao.setText("");
            txtFldDescricao.setText("");
            txtFldFrete.setText("");
            txtFldId.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(eCommercePedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void UpdatePedido(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String situacao   = txtFldSituacao.getText();
        String descricao  = txtFldDescricao.getText();
        String frete      = txtFldFrete.getText();

        try {
            pst = con.prepareStatement("update pedido  set situacao = ?, descricao = ?, frete = ? where idPedido = ?");
            pst.setString(1, situacao);
            pst.setString(2, descricao);
            pst.setString(3, frete);
            pst.setInt(4, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualizando informações do Pedido");
            alert.setHeaderText("Registro do Pedido:");
            alert.setContentText("Registro Atualizado com sucesso!");
            alert.showAndWait();

            table();
            txtFldId.setText("");
            txtFldSituacao.setText("");
            txtFldDescricao.setText("");
            txtFldFrete.setText("");
            txtFldId.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(eCommercePedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void mainPedido(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<Pedido> pedidos = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select idPedido, situacao, descricao, frete from pedido");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Pedido  pedido = new Pedido();
                pedido.setId(rs.getString("idPedido"));
                pedido.setSituacao(rs.getString("situacao"));
                pedido.setDescricao(rs.getString("descricao"));
                pedido.setFrete(rs.getString("frete"));
                pedidos.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommercePedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(pedidos);

        // Assuming your Pedido class has appropriate methods getIdProperty, getSituacao, and so on...
        IdClm.setCellValueFactory(f -> f.getValue().idProperty());

        SituacaoClm.setCellValueFactory(f -> {
            String SituacaoValue = f.getValue().getSituacao();
            return new SimpleStringProperty(SituacaoValue);
        });
        DescricaoClm.setCellValueFactory(f -> {
            String DescricaoValue = f.getValue().getDescricao();
            return new SimpleStringProperty(DescricaoValue);
        });
        FreteClm.setCellValueFactory(f -> {
            String FreteValue = f.getValue().getFrete();
            return new SimpleStringProperty(FreteValue);
        });
    }

    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MeuEsquemaEcommerce", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(eCommercePedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
