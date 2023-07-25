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

public class eCommerceClienteController {

    @FXML
    private TableView<Cliente> table;

    @FXML
    private TableColumn<Cliente, String> IdClm;

    @FXML
    private TableColumn<Cliente, String> NomeClm;

    @FXML
    private TableColumn<Cliente, String> EnderecoClm;

    @FXML
    private TableColumn<Cliente, String> CnpjClm;

    @FXML
    private TableColumn<Cliente, String> CpfClm;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnMain;

    @FXML
    private TextField txtFldId;

    @FXML
    private TextField txtFldNome;

    @FXML
    private TextField txtFldEndereco;

    @FXML
    private TextField txtFldCpf;

    @FXML
    private TextField txtFldCnpj;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectCliente();
        });

        table.setOnKeyReleased(event -> {
            selectCliente();
        });

        table.getSelectionModel().selectFirst();
        selectCliente();
    }


    private void selectCliente() {
        Cliente selectedCliente = (Cliente) table.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            txtFldId.setText(selectedCliente.getId());
            txtFldNome.setText(selectedCliente.getNome());
            txtFldEndereco.setText(selectedCliente.getEndereco());
            txtFldCpf.setText(selectedCliente.getCpf());
            txtFldCnpj.setText(selectedCliente.getCnpj());
        }
    }

    @FXML
    void AddCliente(ActionEvent event) {

        String id         = txtFldId.getText();
        String nome       = txtFldNome.getText();
        String endereco   = txtFldEndereco.getText();
        String cpf        = txtFldCpf.getText();
        String cnpj       = txtFldCnpj.getText();
        try {
            pst = con.prepareStatement("insert into cliente(idCliente, Nome, Endereco, IdentificacaoCPF, IdentificacaoCNPJ) values(?,?,?,?,?)");
            pst.setString(1, id);
            pst.setString(2, nome);
            pst.setString(3, endereco);
            pst.setString(4, cpf);
            pst.setString(5, cnpj);
            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso !!");
                alert.setHeaderText("Registrando o cliente");
                alert.setContentText("Registro adicionado com sucesso.");
                alert.showAndWait();

                table();
                txtFldId.setText("");
                txtFldNome.setText("");
                txtFldEndereco.setText("");
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
            Logger.getLogger(eCommerceClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void DelCliente(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String nome       = txtFldNome.getText();
        String endereco   = txtFldEndereco.getText();
        String cpf        = txtFldCpf.getText();
        String cnpj       = txtFldEndereco.getText();
        try
        {
            pst = con.prepareStatement("delete from cliente where idCliente = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Apagando a selecionada pessoa da tabela Cliente");
            alert.setHeaderText("Registro do Cliente");
            alert.setContentText("Apagado!");
            alert.showAndWait();

            table();
            txtFldId.setText("");
            txtFldNome.setText("");
            txtFldEndereco.setText("");
            txtFldCpf.setText("");
            txtFldCnpj.setText("");
            txtFldId.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(eCommerceClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void UpdateCliente(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String nome       = txtFldNome.getText();
        String endereco   = txtFldEndereco.getText();
        String cpf        = txtFldCpf.getText();
        String cnpj       = txtFldCnpj.getText();

        try {
            pst = con.prepareStatement("update cliente  set Nome= ?, Endereco= ?, IdentificacaoCPF= ?, IdentificacaoCNPJ= ? where idCliente = ?");
            pst.setString(1, nome);
            pst.setString(2, endereco);
            pst.setString(3, cpf);
            pst.setString(4, cnpj);
            pst.setInt(5, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualizando informações do Cliente");
            alert.setHeaderText("Registro do Cliente:");
            alert.setContentText("Registro Atualizado com sucesso!");
            alert.showAndWait();

            table();
            txtFldId.setText("");
            txtFldNome.setText("");
            txtFldEndereco.setText("");
            txtFldCpf.setText("");
            txtFldCnpj.setText("");
            txtFldId.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(eCommerceClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void mainCliente(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("eCommerce.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select idCliente, Nome, Endereco, IdentificacaoCPF, IdentificacaoCNPJ from cliente");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getString("idCliente"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setEndereco(rs.getString("Endereco"));
                cliente.setCpf(rs.getString("IdentificacaoCPF"));
                cliente.setCnpj(rs.getString("IdentificacaoCNPJ"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eCommercePessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(clientes);

        // Assuming your Cliente class has appropriate methods getIdProperty, getNomeProperty, and so on...
        IdClm.setCellValueFactory(f -> f.getValue().idProperty());

        NomeClm.setCellValueFactory(f -> {
            String NomeValue = f.getValue().getNome();
            return new SimpleStringProperty(NomeValue);
        });
        EnderecoClm.setCellValueFactory(f -> {
            String EnderecoValue = f.getValue().getEndereco();
            return new SimpleStringProperty(EnderecoValue);
        });
        CpfClm.setCellValueFactory(f -> {
            String CpfValue = f.getValue().getCpf();
            return new SimpleStringProperty(CpfValue);
        });
        CnpjClm.setCellValueFactory(f -> {
            String CnpjValue = f.getValue().getCnpj();
            return new SimpleStringProperty(CnpjValue);
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
