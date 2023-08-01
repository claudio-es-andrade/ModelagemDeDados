package com.example.gameshopfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Games {
    private final StringProperty id_game;
    private final StringProperty name_game;
    private final StringProperty price;

    public Games() {
        id_game       = new SimpleStringProperty (this, "id_game");
        name_game     = new SimpleStringProperty(this, "name_game");
        price         = new SimpleStringProperty (this, "price");
    }

    // id_category property
    public StringProperty id_gameProperty() { return id_game; }
    public String getId_game() { return id_game.get(); }
    public void setId_game(String newId_game) { id_game.set(newId_game);}

    // name_game property
    public StringProperty name_gameProperty() { return name_game; }
    public String getName_game() { return name_game.get(); }
    public void setName_game(String newName_game) { name_game.set(newName_game); }

    // price property
    public StringProperty priceProperty() { return price; }
    public String getPrice() { return price.get(); }
    public void setPrice(String newPrice) { price.set(newPrice);}

    public String toString() {
            return String.format("[id_game=4s , name_category=50s, price=5s]", getId_game() , getName_game(), getPrice() );
    }

}
