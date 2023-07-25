package com.claudioesandradeecommerce.ecommercemaven;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EstoqueProduto {
    private final StringProperty idEstoque;
    private final StringProperty idProduto;
    private final StringProperty quantidade;


    public EstoqueProduto() {
        idEstoque                  = new SimpleStringProperty(this, "idEstoque");
        idProduto                  = new SimpleStringProperty(this, "idProduto");
        quantidade                 = new SimpleStringProperty(this, "quantidade");
    }
    // idEstoque property
    public StringProperty idEstoque() { return idEstoque; }
    public String getIdEstoque() { return idEstoque.get(); }
    public void setIdEstoque(String newIdEstoque) { idEstoque.set(newIdEstoque);}

    // idProduto property
    public StringProperty idProduto() { return idProduto; }
    public String getIdProduto() { return idProduto.get(); }
    public void setIdProduto(String newIdProduto) { idProduto.set(newIdProduto);}

    // quantidade property
    public StringProperty quantidadeProperty() { return quantidade; }
    public String getQuantidade() { return quantidade.get(); }
    public void setQuantidade(String newQuantidade) { quantidade.set(newQuantidade);}

        public String toString() {
        return String.format("4s[idFornecedor=4s , idProduto=4s]", getIdEstoque(), getIdProduto(), getQuantidade()  );
    }


}
