package com.claudioesandradeecommerce.ecommercemaven;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProdutoPedido {
    private final StringProperty idProduto;
    private final StringProperty idPedido;
    private final StringProperty quantidade;


    public ProdutoPedido() {
        idProduto           = new SimpleStringProperty(this, "idProduto");
        idPedido            = new SimpleStringProperty(this, "idPedido");
        quantidade          = new SimpleStringProperty(this, "quantidade");
    }
    // idProduto property
    public StringProperty idProdutoProperty() { return idProduto; }
    public String getIdProduto() { return idProduto.get(); }
    public void setIdProduto(String newIdProduto) { idProduto.set(newIdProduto);}

    // idPedido property
    public StringProperty idPedidoProperty() { return idPedido; }
    public String getIdPedido() { return idPedido.get(); }
    public void setIdPedido(String newIdPedido) { idPedido.set(newIdPedido);}

    // quantidade property
    public StringProperty quantidadeProperty() { return quantidade; }
    public String getQuantidade() { return quantidade.get(); }
    public void setQuantidade(String newQuantidade) { quantidade.set(newQuantidade);}

    public String toString() {
        return String.format("4s[id=4s , categoria=20s]", getIdProduto(), getIdPedido(), getQuantidade()  );
    }


}
