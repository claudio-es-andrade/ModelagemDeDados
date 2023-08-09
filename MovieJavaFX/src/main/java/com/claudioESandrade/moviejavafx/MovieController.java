package com.claudioESandrade.moviejavafx;

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

public class MovieController {

    @FXML
    private TableView<Movie> table;

    @FXML
    private TableColumn<Movie, String> idClm;

    @FXML
    private TableColumn<Movie, String> typeClm;

    @FXML
    private TableColumn<Movie, String> nameClm;

    @FXML
    private TableColumn<Movie, String> total_epClm;

    @FXML
    private TableColumn<Movie, String> atual_epClm;

    @FXML
    private TableColumn<Movie, String> last_viewClm;

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
    private TextField txtFldType;

    @FXML
    private TextField txtFldName;
    @FXML
    private TextField txtFldTotal_ep;
    @FXML
    private TextField txtFldAtual_ep;
    @FXML
    private TextField txtFldLast_view;


    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectMovie();
        });

        table.setOnKeyReleased(event -> {
            selectMovie();
        });

        table.getSelectionModel().selectFirst();
            selectMovie();
    }

    private void selectMovie() {
        Movie   selectMovies = table.getSelectionModel().getSelectedItem();
        if (selectMovies  != null) {
            txtFldId.setText(selectMovies.getId() );
            txtFldType.setText( selectMovies.getType() );
            txtFldName.setText( selectMovies.getName() );
            txtFldTotal_ep.setText( selectMovies.getTotal_ep() );
            txtFldAtual_ep.setText( selectMovies.getAtual_ep() );
            txtFldLast_view.setText( selectMovies.getLast_view() );
        }
    }

    @FXML
    void Add(ActionEvent event) {
        String id            = txtFldId.getText();
        String type          = txtFldType.getText();
        String name          = txtFldName.getText();
        String total_ep      = txtFldTotal_ep.getText();
        String atual_ep      = txtFldAtual_ep.getText();
        String last_view     = txtFldLast_view.getText();

        try {
            pst = con.prepareStatement("insert into movie(id , type, name, total_ep, atual_ep, last_view) values(?, ?, ?, ?, ?, ?)");
            pst.setString(1, id);
            pst.setString(2, type);
            pst.setString(3, name);
            pst.setString(4, total_ep);
            pst.setString(5, atual_ep);
            pst.setString(6, last_view);

            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success !!");
                alert.setHeaderText("Registering the selected movie");
                alert.setContentText("Register added with success.");
                alert.showAndWait();

                table();
                txtFldId.setText("");
                txtFldType.setText("");
                txtFldName.setText("");
                txtFldTotal_ep.setText("");
                txtFldAtual_ep.setText("");
                txtFldLast_view.setText("");

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Adding the selected movie...");
                alert.setContentText("Fail to add the selected movie.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void Del(ActionEvent event) {

        int myIndex       = table.getSelectionModel().getSelectedIndex();
        int id            = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId() ));
        String type          = txtFldType.getText();
        String name          = txtFldName.getText();
        String total_ep      = txtFldTotal_ep.getText();
        String atual_ep      = txtFldAtual_ep.getText();
        String last_view     = txtFldLast_view.getText();


        try
        {
            pst = con.prepareStatement("delete from movie where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleting the selected movie");
            alert.setHeaderText("Register of movie ");
            alert.setContentText("DELETED!");
            alert.showAndWait();

            table();
            txtFldId.setText("");
            txtFldType.setText("");
            txtFldName.setText("");
            txtFldTotal_ep.setText("");
            txtFldAtual_ep.setText("");
            txtFldLast_view.setText("");

        }
        catch (SQLException ex)
        {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Update(ActionEvent event) {

        int myIndex          = table.getSelectionModel().getSelectedIndex();
        int id               = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId() ));
        String type          = txtFldType.getText();
        String name          = txtFldName.getText();
        String total_ep      = txtFldTotal_ep.getText();
        String atual_ep      = txtFldAtual_ep.getText();
        String last_view     = txtFldLast_view.getText();

        try {
            pst = con.prepareStatement("update movie set type = ?, name = ?, total_ep = ?, atual_ep = ?, last_view = ? where id = ?");
            pst.setString(1, type);
            pst.setString(2, name);
            pst.setString(3, total_ep);
            pst.setString(4, atual_ep);
            pst.setString(5, last_view);
            pst.setString(6, String.valueOf ( id ) );
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Updating Movie");
            alert.setHeaderText("Movie Register");
            alert.setContentText("Updated Register with success!");
            alert.showAndWait();

            table();
            txtFldId.setText("");
            txtFldType.setText("");
            txtFldName.setText("");
            txtFldTotal_ep.setText("");
            txtFldAtual_ep.setText("");
            txtFldLast_view.setText("");
            txtFldId.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void main(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("movieMain.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<Movie> movies  =  FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select id, type, name, total_ep, atual_ep, last_view from movie");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Movie movie  = new Movie();
                movie.setId( rs.getString("id") );
                movie.setType(rs.getString("type") );
                movie.setName(rs.getString( "name") );
                movie.setTotal_ep(rs.getString( "total_ep") );
                movie.setAtual_ep(rs.getString( "atual_ep") );
                movie.setLast_view(rs.getString( "last_view") );
                movies.add(movie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(movies);

        idClm.setCellValueFactory(f -> {
            String idValue = f.getValue().getId();
            return new SimpleStringProperty( idValue );
        });
        typeClm.setCellValueFactory(f -> {
            String typeValue = f.getValue().getType();
            return new SimpleStringProperty( typeValue );
        });
        nameClm.setCellValueFactory(f -> {
            String nameValue = f.getValue().getName();
            return new SimpleStringProperty(nameValue );
        });
        total_epClm.setCellValueFactory(f -> {
            String total_epValue = f.getValue().getTotal_ep();
            return new SimpleStringProperty ( total_epValue );
        });
        atual_epClm.setCellValueFactory(f -> {
            String atual_epValue = f.getValue().getAtual_ep();
            return new SimpleStringProperty( atual_epValue );
        });
        last_viewClm.setCellValueFactory(f -> {
            String last_viewValue = f.getValue().getLast_view();
            return new SimpleStringProperty( last_viewValue );
        });

    }


    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movieCatalog", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}