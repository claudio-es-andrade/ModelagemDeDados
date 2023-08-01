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

public class OrdersController {

    @FXML
    private TableView<Orders> table;

    @FXML
    private TableColumn<Orders, String> id_orderClm;

    @FXML
    private TableColumn<Orders, String> qtyClm;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnMain;

    @FXML
    private TextField txtFldId_order;

    @FXML
    private TextField txtFldQty;

    Connection con;
    PreparedStatement pst;

    public void initialize() {
        Connect();
        table();
        table.setOnMouseClicked(event -> {
            selectOrders();
        });

        table.setOnKeyReleased(event -> {
            selectOrders();
        });

        table.getSelectionModel().selectFirst();
        selectOrders();
    }

    private void selectOrders() {
        Orders   selectedOrders = table.getSelectionModel().getSelectedItem();
        if (selectedOrders  != null) {
            txtFldId_order.setText( selectedOrders.getId_order() );
            txtFldQty.setText( selectedOrders.getQty() );
        }
    }

    @FXML
    void AddOrder(ActionEvent event) {
        String id_order     = txtFldId_order.getText();
        String qty          = txtFldQty.getText();

        try {
            pst = con.prepareStatement("insert into orders(id_order, qty) values(?, ?)");
            pst.setString(1, id_order);
            pst.setString(2, qty);

            int status = pst.executeUpdate();

            if (status == 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success !!");
                alert.setHeaderText("Registering the selected order");
                alert.setContentText("Register added with success.");
                alert.showAndWait();

                table();
                txtFldId_order.setText("");
                txtFldQty.setText("");

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Adding the selected order...");
                alert.setContentText("Fail to add the selected order.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void DelOrder(ActionEvent event) {

        int myIndex           = table.getSelectionModel().getSelectedIndex();
        int id                = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId_order () ));
        String Qty  = txtFldQty.getText ();


        try
        {
            pst = con.prepareStatement("delete from orders where id_order = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleting the selected order");
            alert.setHeaderText("Register of order: ");
            alert.setContentText("DELETED!");
            alert.showAndWait();

            table();
            txtFldId_order.setText("");
            txtFldQty.setText("");

        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void UpdateOrder(ActionEvent event) {

        int myIndex          = table.getSelectionModel().getSelectedIndex();
        int id               = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId_order() ));
        String qty   = txtFldQty.getText();

        try {
            pst = con.prepareStatement("update orders set qty = ? where id_order = ?");
            pst.setString(1, qty );
            pst.setString(2, String.valueOf ( id ) );
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Updating Orders");
            alert.setHeaderText("Order Register");
            alert.setContentText("Updated Register with success!");
            alert.showAndWait();

            table();
            txtFldId_order.setText("");
            txtFldQty.setText("");
            txtFldId_order.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void mainOrders(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameShop.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void table() {
        ObservableList<Orders> orderss  =  FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select id_order, qty from orders");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Orders  orders  = new Orders ();
                orders.setId_order ( rs.getString("id_order"));
                orders.setQty(rs.getString("qty"));
                orderss.add(orders);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(orderss);

        id_orderClm.setCellValueFactory(f -> {
            String id_orderValue = f.getValue ().getId_order ();
            return new SimpleStringProperty ( id_orderValue );
        });
        qtyClm.setCellValueFactory(f -> {
            String qtyValue = f.getValue().getQty ();
            return new SimpleStringProperty(qtyValue );
        });

    }
    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gameShop", "root", "AguaSuja1@");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}


