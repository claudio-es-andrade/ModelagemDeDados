package com.claudioESandrade.moviejavafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Movie {
    private final StringProperty id;
    private final StringProperty type;
    private final StringProperty name;
    private final  StringProperty total_ep;
    private final  StringProperty atual_ep;
    private final  StringProperty last_view;


    public Movie() {
        id              = new SimpleStringProperty(this, "id");
        type            = new SimpleStringProperty(this, "type");
        name            = new SimpleStringProperty(this, "name");
        total_ep        = new SimpleStringProperty(this, "total_ep");
        atual_ep        = new SimpleStringProperty(this, "atual_ep");
        last_view       = new SimpleStringProperty(this, "last_view");
    }
    // id property
    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId);}

    // type property
    public StringProperty typeProperty() { return type; }
    public String getType() { return type.get(); }
    public void setType(String newType) { type.set(newType);}

    // name property
    public StringProperty nameProperty() { return name; }
    public String getName() { return name.get(); }
    public void setName(String newName) { name.set(newName);}

    // total_ep property
    public StringProperty total_epProperty() { return total_ep; }
    public String getTotal_ep() { return total_ep.get(); }
    public void setTotal_ep(String newTotal_ep) { total_ep.set(newTotal_ep);}

    // atual_ep property
    public StringProperty atual_epProperty() { return atual_ep; }
    public String getAtual_ep() { return atual_ep.get(); }
    public void setAtual_ep(String newAtual_ep) { atual_ep.set(newAtual_ep);}

    // last_view property
    public StringProperty last_viewProperty() { return last_view; }
    public String getLast_view() { return last_view.get(); }
    public void setLast_view(String newLast_view) { last_view.set(newLast_view);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;
        return Objects.equals ( getId (), movie.getId () ) && Objects.equals ( getType (), movie.getType () ) && Objects.equals ( getName (), movie.getName () ) && Objects.equals ( getTotal_ep (), movie.getTotal_ep () ) && Objects.equals ( getAtual_ep (), movie.getAtual_ep () ) && Objects.equals ( getLast_view (), movie.getLast_view () );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( getId (), getType (), getName (), getTotal_ep (), getAtual_ep (), getLast_view () );
    }

    @Override
    public String toString() {
        return " Movie {" +
                " id = " + id +
                ", type = " + type +
                ", name = " + name +
                ", total_ep = " + total_ep +
                ", atual_ep = " + atual_ep +
                ", last_view = " + last_view +
                '}';
    }
}
