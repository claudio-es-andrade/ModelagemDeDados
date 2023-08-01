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

public class CategoriesController {

    @FXML
    private TableView<Categories> table;

    @FXML
    private TableColumn<Categories, String> id_categoryClm;

    @FXML
    private TableColumn<Categories, String> name_categoryClm;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnMain;

    @FXML
    private TextField txtFldId_category;

    @FXML
    private TextField txtFldName_category;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectCategories();
        });

        table.setOnKeyReleased(event -> {
            selectCategories();
        });

        table.getSelectionModel().selectFirst();
            selectCategories();
    }

    private void selectCategories() {
        Categories   selectedCategories = table.getSelectionModel().getSelectedItem();
        if (selectedCategories  != null) {
            txtFldId_category.setText(selectedCategories.getId_category() );
            txtFldName_category.setText( selectedCategories.getName_category() );
        }
    }

    @FXML
    void AddCategory(ActionEvent event) {
        String id_category     = txtFldId_category.getText();
        String name_category   = txtFldName_category.getText();

        try {
            pst = con.prepareStatement("insert into categories(id_category, name_category) values(?, ?)");
            pst.setString(1, id_category);
            pst.setString(2, name_category);

            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success !!");
                alert.setHeaderText("Registering the selected category");
                alert.setContentText("Register added with success.");
                alert.showAndWait();

                table();
                txtFldId_category.setText("");
                txtFldName_category.setText("");

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Adding the selected category...");
                alert.setContentText("Fail to add the selected category.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void DelCategory(ActionEvent event) {

        int myIndex           = table.getSelectionModel().getSelectedIndex();
        int id                = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId_category() ));
        String name_category  = txtFldName_category.getText();


        try
        {
            pst = con.prepareStatement("delete from categories where id_category = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleting the selected category");
            alert.setHeaderText("Register of category: ");
            alert.setContentText("DELETED!");
            alert.showAndWait();

            table();
            txtFldId_category.setText("");
            txtFldName_category.setText("");

        }
        catch (SQLException ex)
        {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void UpdateCategory(ActionEvent event) {

        int myIndex          = table.getSelectionModel().getSelectedIndex();
        int id               = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId_category() ));
        String name_category   = txtFldName_category.getText();

        try {
            pst = con.prepareStatement("update categories set name_category = ? where id_category = ?");
            pst.setString(1, name_category );
            pst.setString(2, String.valueOf( id ) );
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Updating Categories");
            alert.setHeaderText("Category Register");
            alert.setContentText("Updated Register with success!");
            alert.showAndWait();

            table();
            txtFldId_category.setText("");
            txtFldName_category.setText("");
            txtFldId_category.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void mainCategories(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameShop.fxml")));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<Categories> categorizes  =  FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select id_category, name_category from categories");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Categories categories  = new Categories();
                categories.setId_category( rs.getString("id_category"));
                categories.setName_category(rs.getString("name_category"));
                categorizes.add(categories);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(categorizes);

        id_categoryClm.setCellValueFactory(f -> {
            String id_categoryValue = f.getValue().getId_category();
            return new SimpleStringProperty( id_categoryValue );
        });
        name_categoryClm.setCellValueFactory(f -> {
            String name_categoryValue = f.getValue().getName_category ();
            return new SimpleStringProperty(name_categoryValue );
        });

    }
    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gameShop", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

