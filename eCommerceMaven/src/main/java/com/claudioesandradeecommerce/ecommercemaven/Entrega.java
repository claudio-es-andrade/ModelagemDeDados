package com.claudioesandradeecommerce.ecommercemaven;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Entrega {
    private final StringProperty id;
    private final StringProperty codigoRastreio;
    private final StringProperty situacao;


    public Entrega() {
        id                  = new SimpleStringProperty(this, "id");
        codigoRastreio      = new SimpleStringProperty(this, "codigoRastreio");
        situacao            = new SimpleStringProperty(this, "situacao");
    }
    // id property
    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId);}

    // codigoRastreio property
    public StringProperty codigoRastreioProperty() { return codigoRastreio; }
    public String getCodigoRastreio() { return codigoRastreio.get(); }
    public void setCodigoRastreio(String newCodigoRastreio) { codigoRastreio.set(newCodigoRastreio);}

    // situacao property
    public StringProperty situacaoProperty() { return situacao; }
    public String getSituacao() { return situacao.get(); }
    public void setSituacao(String newSituacao) { situacao.set(newSituacao);}

    public String toString() {
        return String.format("4s[id=4s , codigoRastreio=45s]", getId(), getCodigoRastreio(), getSituacao()  );
    }

}
