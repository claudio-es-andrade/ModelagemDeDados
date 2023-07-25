package com.claudioesandradeecommerce.ecommercemaven;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pedido {
    private final StringProperty id;
    private final StringProperty situacao;
    private final StringProperty descricao;
    private final StringProperty frete;

    public Pedido() {
        id           = new SimpleStringProperty(this, "id");
        situacao     = new SimpleStringProperty(this, "situacao");
        descricao    = new SimpleStringProperty(this, "descricao");
        frete        = new SimpleStringProperty(this, "frete");

    }
    // id property
    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId);}
    // situacao property
    public StringProperty situacaoProperty() { return situacao; }
    public String getSituacao() { return situacao.get(); }
    public void setSituacao(String newSituacao) { situacao.set(newSituacao);}
    // descricao property
    public StringProperty descricaoProperty() { return descricao; }
    public String getDescricao() { return descricao.get(); }
    public void setDescricao(String newDescricao) { descricao.set(newDescricao);}
    // frete property
    public StringProperty freteProperty() { return frete; }
    public String getFrete() { return frete.get(); }
    public void setFrete(String newFrete) { frete.set(newFrete);}

    public String toString() {
        return String.format("4s[id=4s , nome=45s]", getId(), getSituacao(), getDescricao(), getFrete() );
    }


}
