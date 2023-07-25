package com.claudioesandradeecommerce.ecommercemaven;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

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

public class eCommercePagamentoModifiedController {
    @FXML
    private TableColumn<Pagamento, String> IdClm;

    @FXML
    private TableColumn<Pagamento, String> IdentificacaoClm;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnMain;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Pagamento> table;

    @FXML
    private TextField txtFldId;

    @FXML
    private TextField txtFldIdentificacao;

    // @FXML
    // private ChoiceBox<String> choiceIdentificacao;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectPagamento();
        });

        table.setOnKeyReleased(event -> {
            selectPagamento();
        });

        table.getSelectionModel().selectFirst();
        selectPagamento();
//        choiceIdentificacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            // You can perform any necessary actions when an item is selected in the ChoiceBox.
//            // For example, if you want to display the selected value in a label, you can do it here.
//            System.out.println("Selected item: " + newValue);
//        });
    }


    private void selectPagamento() {
        Pagamento selectedPagamento = (Pagamento) table.getSelectionModel().getSelectedItem();
        if (selectedPagamento != null) {
            txtFldId.setText(selectedPagamento.getId());
            //choiceIdentificacao.setValue(selectedPagamento.getIdentificacao());
             txtFldIdentificacao.setText(selectedPagamento.getIdentificacao());
        }
    }

    @FXML
    void AddPagamento(ActionEvent event) {
        String id                = txtFldId.getText();
        // String identificacao = choiceIdentificacao.getValue();
         String identificacao     = txtFldIdentificacao.getText();

        try {
            pst = con.prepareStatement("insert into pagamento(idPagamento,identificacao ) values(?,?)");
            pst.setString(1, id);
            pst.setString(2, identificacao);
            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso !!");
                alert.setHeaderText("Adicionando o tipo de pagamento da pessoa");
                alert.setContentText("Registro adicionado com sucesso.");
                alert.showAndWait();

                table();
                txtFldId.setText("");
                txtFldIdentificacao.setText("");
                txtFldId.requestFocus();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Falha");
                alert.setHeaderText("Adicionando o tipo de pagamento da pessoa");
                alert.setContentText("Falha na adição do Registro.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommercePagamentoModifiedController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @FXML
    void DelPagamento(ActionEvent event) {
        // String identificacao = choiceIdentificacao.getValue();
        String identificacao      = txtFldIdentificacao.getText();

        int myIndex = table.getSelectionModel().getSelectedIndex();
        int id      = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));


        try
        {
            pst = con.prepareStatement("delete from pagamento where idPagamento = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Apagando o tipo de pagamento da Pessoa");
            alert.setHeaderText("Registro do tipo de pagaemnto");
            alert.setContentText("Apagado!");
            alert.showAndWait();

            table();
            txtFldId.setText("");
            txtFldIdentificacao.setText("");
            txtFldId.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(eCommercePessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void UpdatePagamento(ActionEvent event) {
        int myIndex = table.getSelectionModel().getSelectedIndex();
        int id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        // String identificacao = choiceIdentificacao.getValue();
        String identificacao = txtFldIdentificacao.getText();

        try {
            pst = con.prepareStatement("update pagamento set identificacao = ? where idPagamento = ?");
            pst.setString(1, identificacao);
            pst.setInt(2, id); // Corrected parameter index from 4 to 3
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualizando o tipo de Pagamento");
            alert.setHeaderText("Registro do tipos de Pagamento");
            alert.setContentText("Registro Atualizado com sucesso!");
            alert.showAndWait();

            table();
            txtFldId.setText("");
            txtFldIdentificacao.setText("");
            txtFldId.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(eCommercePagamentoModifiedController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void mainPagamento(ActionEvent event) throws IOException {
            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    public void table() {
        ObservableList<Pagamento> pagamentos = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select idPagamento, identificacao from pagamento");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setId(rs.getString("idPagamento"));
                pagamento.setIdentificacao(rs.getString("identificacao"));
                pagamentos.add(pagamento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommercePagamentoModifiedController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(pagamentos);

        IdClm.setCellValueFactory(f -> f.getValue().idProperty());
        IdentificacaoClm.setCellValueFactory(f -> {
            String identificacaoValue = f.getValue().getIdentificacao();
            return new SimpleStringProperty(identificacaoValue);
        });

    }

    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MeuEsquemaEcommerce", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(eCommercePagamentoModifiedController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
