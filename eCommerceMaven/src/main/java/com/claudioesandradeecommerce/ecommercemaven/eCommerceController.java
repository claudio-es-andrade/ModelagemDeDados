package com.claudioesandradeecommerce.ecommercemaven;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.control.Button;


public class eCommerceController {

    @FXML
    private Button btnCliente;

    @FXML
    private Button btnEntrega;

    @FXML
    private Button btnFornecedor;

    @FXML
    private Button btnFornecedorEntrega;

    @FXML
    private Button btnFornecedorProduto;

    @FXML
    private Button btnPagamento;

    @FXML
    private Button btnPedido;

    @FXML
    private Button btnPessoa;

    @FXML
    private Button btnProduto;

    @FXML
    private Button btnProdutoPedido;

    @FXML
    private Button btnVendedorTerceirizado;

    @FXML
    private Button btnVendedorTerceirizadoproduto;

    @FXML
    private Button btnEstoque;

    @FXML
    private Button btnEstoqueProduto;

    @FXML
    private Button btnNota;

    @FXML
    void pessoa(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pessoa.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void pagamento(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pagamentoOriginal_01.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void cliente(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cliente.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void pedido(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pedido.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void produto(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("produto.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void produtoPedido(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("produtoPedido.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void fornecedor(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fornecedor.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void fornecedorProduto(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fornecedorProduto.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void entrega(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("entrega.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void fornecedorEntrega(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fornecedorEntrega.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void vendedorTerceirizado(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("vendedorTerceirizado.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void vendedorTerceirizadoProduto(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("vendedorTerceirizadoProduto.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void estoque(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("estoque.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void estoqueProduto(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("estoqueProduto.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void nota(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("nota.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
