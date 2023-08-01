package com.example.gameshopfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Orders {
    private final StringProperty id_order;
    private final StringProperty qty;


    public Orders() {
        id_order            = new SimpleStringProperty(this, "id_order");
        qty                 = new SimpleStringProperty(this, "qty");
    }
    // idOrders property
    public StringProperty id_order() { return id_order; }
    public String getId_order() { return id_order.get(); }
    public void setId_order(String newId_order) { id_order.set(newId_order);}

    // qty property
    public StringProperty qtyProperty() { return qty; }
    public String getQty() { return qty.get(); }
    public void setQty(String newQty) { qty.set(newQty);}

    public String toString() {
        return String.format("4s[id_order=4s , qty=4s]", getId_order(), getQty()  );
    }
}
