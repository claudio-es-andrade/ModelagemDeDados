package com.example.gameshopfx;

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

public class GameShopController {

    @FXML
    private Button btnGames;

    @FXML
    private Button btnCategories;

    @FXML
    private Button btnClients;

    @FXML
    private Button btnORDERS;

    @FXML
    private Button btnINVOICES;

    @FXML
    void games(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("games.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void categories(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("categories.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clients(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("clients.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void orders(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("orders.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void invoices(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("invoices.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
    }

}

