package com.claudioESandrade.moviejavafx;

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

public class MovieMainController {

    @FXML
    private Button btnMovies;


    @FXML
    void movies(ActionEvent event) throws IOException{
        AnchorPane root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("movie.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
    }
}
