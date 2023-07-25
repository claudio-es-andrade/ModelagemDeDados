package com.claudioesandradeecommerce.ecommercemaven;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Estoque {
    private final StringProperty id;
    private final StringProperty endereco;
    private final StringProperty descricao;
    private final StringProperty quantidade;

    public Estoque() {
        id                      = new SimpleStringProperty(this, "id");
        endereco                = new SimpleStringProperty(this, "endereco");
        descricao               = new SimpleStringProperty(this, "descricao");
        quantidade              = new SimpleStringProperty(this, "quantidade");
    }
    // id property
    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId);}

    // endereco property
    public StringProperty enderecoProperty() { return endereco; }
    public String getEndereco() { return endereco.get(); }
    public void setEndereco(String newEndereco) { endereco.set(newEndereco);}

    // descricao property
    public StringProperty descricaoProperty() { return descricao; }
    public String getDescricao() { return descricao.get(); }
    public void setDescricao(String newDescricao) { descricao.set(newDescricao);}

    // quantidade property
    public StringProperty quantidadeProperty() { return quantidade; }
    public String getQuantidade() { return quantidade.get(); }
    public void setQuantidade(String newQuantidade) { quantidade.set(newQuantidade);}

    public String toString() {
        return String.format("4s[id=4s , Endereco=45s]", getId(), getEndereco(), getDescricao(), getQuantidade() );
    }
}
