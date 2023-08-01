package com.example.gameshopfx;

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

public class GamesController {

    @FXML
    private TableView<Games> table;

    @FXML
    private TableColumn<Games, String> id_gameClm;

    @FXML
    private TableColumn<Games, String> name_gameClm;

    @FXML
    private TableColumn<Games, String> priceClm;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnMain;

    @FXML
    private TextField txtFldId_game;

    @FXML
    private TextField txtFldName_game;

    @FXML
    private TextField txtFldPrice;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectGames();
        });

        table.setOnKeyReleased(event -> {
            selectGames();
        });

        table.getSelectionModel().selectFirst();
            selectGames();
    }

    private void selectGames() {
        Games   selectGames = table.getSelectionModel().getSelectedItem();
        if (selectGames  != null) {
            txtFldId_game.setText(selectGames.getId_game() );
            txtFldName_game.setText(selectGames.getName_game() );
            txtFldPrice.setText( selectGames.getPrice() );
        }
    }

    @FXML
    void AddGame(ActionEvent event) {
        String id_game        = txtFldId_game.getText();
        String name_game      = txtFldName_game.getText();
        String price          = txtFldPrice.getText();

        try {
            pst = con.prepareStatement("insert into games(id_game, name_game, price) values(?, ?, ?)");
            pst.setString(1, id_game);
            pst.setString(2, name_game);
            pst.setString( 3, price );

            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success !!");
                alert.setHeaderText("Registering the selected game");
                alert.setContentText("Register added with success.");
                alert.showAndWait();

                table();
                txtFldId_game.setText("");
                txtFldName_game.setText("");
                txtFldPrice.setText("");

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Adding the selected game...");
                alert.setContentText("Fail to add the selected game.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GamesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void DelGame(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId_game() ));
        String name_game  = txtFldName_game.getText();
        String price      = txtFldPrice.getText();


        try
        {
            pst = con.prepareStatement("delete from games where id_game = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleting the selected game");
            alert.setHeaderText("Register of game ");
            alert.setContentText("DELETED!");
            alert.showAndWait();

            table();
            txtFldId_game.setText("");
            txtFldName_game.setText("");
            txtFldPrice.setText("");

        }
        catch (SQLException ex)
        {
            Logger.getLogger(GamesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void UpdateGame(ActionEvent event) {

        int myIndex          = table.getSelectionModel().getSelectedIndex();
        int id               = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId_game() ));
        String name_game     = txtFldName_game.getText();
        String price         = txtFldPrice.getText();

        try {
            pst = con.prepareStatement("update games set name_game = ?, price = ?  where id_game = ?");
            pst.setString(1, name_game );
            pst.setString(2, price );
            pst.setString(3, String.valueOf( id ) );
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Updating Games");
            alert.setHeaderText("Game Register");
            alert.setContentText("Updated Register with success!");
            alert.showAndWait();

            table();
            txtFldId_game.setText("");
            txtFldName_game.setText("");
            txtFldPrice.setText("");
            txtFldId_game.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(GamesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void mainGames(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameShop.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<Games> gamest  =  FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select id_game, name_game, price from games");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Games  games = new Games();
                games.setId_game ( rs.getString("id_game"));
                games.setName_game(rs.getString("name_game"));
                games.setPrice ( rs.getString("price"));
                gamest.add(games);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GamesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(gamest);

        id_gameClm.setCellValueFactory(f -> {
            String id_gameValue = f.getValue().getId_game();
            return new SimpleStringProperty( id_gameValue );
        });
        name_gameClm.setCellValueFactory(f -> {
            String name_gameValue = f.getValue().getName_game();
            return new SimpleStringProperty(name_gameValue );
        });
        priceClm.setCellValueFactory(f -> {
            String priceValue = f.getValue().getPrice();
            return new SimpleStringProperty(priceValue );
        });

    }


    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gameShop", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GamesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

