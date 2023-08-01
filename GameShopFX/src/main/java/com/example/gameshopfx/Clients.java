package com.example.gameshopfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Clients {
    private final StringProperty id_client;
    private final StringProperty name_client;

    public Clients() {
            id_client       = new SimpleStringProperty(this, "id_client");
            name_client     = new SimpleStringProperty(this, "name_client");
    }
    // id_client property
    public StringProperty id_clientProperty() { return id_client; }
    public String getId_client() { return id_client.get(); }
    public void setId_client(String newId_client) { id_client.set(newId_client);}

    // name_client property
    public StringProperty name_clientProperty() { return name_client; }
    public String getName_client() { return name_client.get(); }
    public void setName_client(String newName_client) { name_client.set(newName_client);}

    public String toString() {
      return String.format("[id_client=4s , name_client=100s]", getId_client() , getName_client() );
    }
}

