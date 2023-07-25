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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class eCommercePessoaController {
        @FXML
        private TableView<Pessoa> table;

        @FXML
        private TableColumn<Pessoa, String> IdClm;

        @FXML
        private TableColumn<Pessoa, String> FClm;

        @FXML
        private TableColumn<Pessoa, String> JClm;

        @FXML
        private Button btnAdd;

        @FXML
        private Button btnDel;

        @FXML
        private Button btnMain;

        @FXML
        private Button btnUpdate;

        @FXML
        private TextField txtFldCnpj;

        @FXML
        private TextField txtFldCpf;

        @FXML
        private TextField txtFldId;

        Connection con;
        PreparedStatement pst;

        public void initialize() {
            Connect();
            table();
            table.setOnMouseClicked(event -> {
                selectPessoa();
            });

            table.setOnKeyReleased(event -> {
                selectPessoa();
            });

            table.getSelectionModel().selectFirst();
            selectPessoa();
        }

        private void selectPessoa() {
            Pessoa selectedPessoa = table.getSelectionModel().getSelectedItem();
            if (selectedPessoa != null) {
                txtFldId.setText(selectedPessoa.getId());
                txtFldCpf.setText(selectedPessoa.getCpf());
                txtFldCnpj.setText(selectedPessoa.getCnpj());
            }
        }

        @FXML
        void AddPessoa(ActionEvent event) {

            // Connect();
            // table();
            String id      = txtFldId.getText();
            String cpf     = txtFldCpf.getText();
            String cnpj    = txtFldCnpj.getText();
            try {
                pst = con.prepareStatement("insert into pessoa(idPessoa,tFisica,tJuridica) values(?,?,?)");
                pst.setString(1, id);
                pst.setString(2, cpf);
                pst.setString(3, cnpj);
                int status = pst.executeUpdate();

                if (status == 1) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sucesso !!");
                    alert.setHeaderText("Adicionando o tipo de cadastro de uma pessoa");
                    alert.setContentText("Registro adicionado com sucesso.");
                    alert.showAndWait();

                    table();
                    txtFldId.setText("");
                    txtFldCpf.setText("");
                    txtFldCnpj.setText("");
                    txtFldId.requestFocus();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Falha");
                    alert.setHeaderText("Adicionando o tipo de cadastro de uma pessoa");
                    alert.setContentText("Falha na adição do Registro.");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(eCommercePessoaController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        @FXML
        void DelPessoa(ActionEvent event) {

            // Connect();
            // table();

            String Cpf      = txtFldCpf.getText();
            String Cnpj     = txtFldCnpj.getText();

            int myIndex = table.getSelectionModel().getSelectedIndex();
            int id      = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));


            try
            {
                pst = con.prepareStatement("delete from pessoa where idPessoa = ? ");
                pst.setInt(1, id);
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Apagando o tipo de cadastro de uma Pessoa");
                alert.setHeaderText("Registro da Pessoa");
                alert.setContentText("Apagado!");

                alert.showAndWait();
                table();

            }
            catch (SQLException ex)
            {
                Logger.getLogger(eCommercePessoaController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    @FXML
    void UpdatePessoa(ActionEvent event) {
        // Connect();
        // table();

        int myIndex = table.getSelectionModel().getSelectedIndex();
        int id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String cpf = txtFldCpf.getText();
        String cnpj = txtFldCnpj.getText();

        try {
            pst = con.prepareStatement("update pessoa set tFisica = ?, tJuridica = ? where idPessoa = ?");
            pst.setString(1, cpf);
            pst.setString(2, cnpj);
            pst.setInt(3, id); // Corrected parameter index from 4 to 3
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualizando o tipo de Cadastro das Pessoas");
            alert.setHeaderText("Registro dos tipos das Pessoas");
            alert.setContentText("Atualizado com sucesso!");
            alert.showAndWait();

            table();
        } catch (SQLException ex) {
            Logger.getLogger(eCommercePessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void mainPessoa(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select idPessoa, tFisica, tJuridica from pessoa");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getString("idPessoa"));
                pessoa.setCpf(rs.getString("tFisica"));
                pessoa.setCnpj(rs.getString("tJuridica"));
                pessoas.add(pessoa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommercePessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(pessoas);

        // Assuming your Pessoa class has appropriate methods getIdProperty, getCpfProperty, and getCnpjProperty
        IdClm.setCellValueFactory(f -> f.getValue().idProperty());
        FClm.setCellValueFactory(f -> {
            String tFisicaValue = f.getValue().getCpf();
            return new SimpleStringProperty(tFisicaValue.equals("1") ? "true" : "false");
        });
        JClm.setCellValueFactory(f -> {
            String tJuridicaValue = f.getValue().getCnpj();
            return new SimpleStringProperty(tJuridicaValue.equals("1") ? "true" : "false");
        });
    }


        private void Connect() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MeuEsquemaEcommerce", "root", "AguaSuja1@");
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(eCommercePessoaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}