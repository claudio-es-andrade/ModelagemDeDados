package com.example.gameshopfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameShopApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader ( GameShopApplication.class.getResource ( "gameShop.fxml" ) );
        Scene scene = new Scene ( fxmlLoader.load (), 1000, 600 );
        stage.setTitle ( "GAME SHOP DATABASE" );
        stage.setScene ( scene );
        stage.show ();
    }

    public static void main(String[] args) {
        launch ();
    }
}