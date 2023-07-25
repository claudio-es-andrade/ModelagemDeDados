package com.claudioesandradeecommerce.ecommercemaven;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Produto {
    private final StringProperty id;
    private final StringProperty categoria;
    private final StringProperty descricao;
    private final StringProperty valor;

    public Produto() {
        id           = new SimpleStringProperty(this, "id");
        categoria    = new SimpleStringProperty(this, "categoria");
        descricao    = new SimpleStringProperty(this, "descricao");
        valor        = new SimpleStringProperty(this, "valor");

    }
    // id property
    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId);}
    // categoria property
    public StringProperty categoriaProperty() { return categoria; }
    public String getCategoria() { return categoria.get(); }
    public void setCategoria(String newCategoria) { categoria.set(newCategoria);}
    // descricao property
    public StringProperty descricaoProperty() { return descricao; }
    public String getDescricao() { return descricao.get(); }
    public void setDescricao(String newDescricao) { descricao.set(newDescricao);}
    // valor property
    public StringProperty valorProperty() { return valor; }
    public String getValor() { return valor.get(); }
    public void setValor(String newValor) { valor.set(newValor);}

    public String toString() {
        return String.format("4s[id=4s , categoria=20s]", getId(), getCategoria(), getDescricao(), getValor()  );
    }


}
