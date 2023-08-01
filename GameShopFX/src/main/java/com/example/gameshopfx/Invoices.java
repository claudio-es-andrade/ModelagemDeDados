package com.example.gameshopfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Invoices {

    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty qty;
    private final StringProperty game;
    private final StringProperty price;
    private final StringProperty category;

    public Invoices() {

        id         = new SimpleStringProperty (this, "id");
        name       = new SimpleStringProperty(this, "name");
        qty        = new SimpleStringProperty(this, "qty");
        game       = new SimpleStringProperty(this, "game");
        price      = new SimpleStringProperty(this, "price");
        category   = new SimpleStringProperty(this, "category");
    }

    // id property
    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId);}

    // name property
    public StringProperty nameProperty() { return name; }
    public String getName() { return name.get(); }
    public void setName(String newName) { name.set(newName);}

    // qty property
    public StringProperty qtyProperty() { return qty; }
    public String getQty() { return qty.get(); }
    public void setQty(String newQty) { qty.set(newQty);}

    // game property
    public StringProperty gameProperty() { return game; }
    public String getGame() { return game.get(); }
    public void setGame(String newGame) { game.set(newGame);}

    // price property
    public StringProperty priceProperty() { return price; }
    public String getPrice() { return price.get(); }
    public void setPrice(String newPrice) { price.set(newPrice);}

    // category property
    public StringProperty categoryProperty() { return category; }
    public String getCategory() { return category.get(); }
    public void setCategory(String newCategory) { category.set(newCategory);}


    public String toString() {
        return String.format("[id=%s, name=%s, qty=%s, game=%s, price=%s, category=%s]",
                getId(), getName(), getQty(), getGame(), getPrice(), getCategory());
    }
}