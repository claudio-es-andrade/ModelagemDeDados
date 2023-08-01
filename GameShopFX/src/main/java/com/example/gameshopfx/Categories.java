package com.example.gameshopfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Categories {
    private final StringProperty id_category;
    private final StringProperty name_category;

    public Categories() {
            id_category       = new SimpleStringProperty (this, "id_category");
            name_category     = new SimpleStringProperty(this, "name_category");
    }
    // id_category property
    public StringProperty id_categoryProperty() { return id_category; }
    public String getId_category() { return id_category.get(); }
    public void setId_category(String newId_category) { id_category.set(newId_category);}

    // name_category property
    public StringProperty name_categoryProperty() { return name_category; }
    public String getName_category() { return name_category.get(); }
    public void setName_category(String newName_category) { name_category.set(newName_category);}

    public String toString() {
        return String.format("[id_category=4s , name_category=100s]", getId_category() , getName_category() );
    }
}
