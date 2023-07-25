package com.claudioesandradeecommerce.ecommercemaven;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VendedorTerceirizadoProduto {
    private final StringProperty idVendedor;
    private final StringProperty idProduto;
    private final StringProperty quantidade;

    public VendedorTerceirizadoProduto() {
        idVendedor              = new SimpleStringProperty(this, "idVendedor");
        idProduto               = new SimpleStringProperty(this, "idProduto");
        quantidade              = new SimpleStringProperty(this, "quantidade");
    }
    // idVendedor property
    public StringProperty idVendedorProperty() { return idVendedor; }
    public String getIdVendedor() { return idVendedor.get(); }
    public void setIdVendedor(String newIdVendedor) { idVendedor.set(newIdVendedor);}
    // idProduto property
    public StringProperty idProdutoProperty() { return idProduto; }
    public String getIdProduto() { return idProduto.get(); }
    public void setIdProduto(String newIdProduto) { idProduto.set(newIdProduto);}
    // quantidade property
    public StringProperty quantidadeProperty() { return quantidade; }
    public String getQuantidade() { return quantidade.get(); }
    public void setQuantidade(String newQuantidade) { quantidade.set(newQuantidade);}

    public String toString() {
        return String.format("4s[idVendedor=4s , idProduto=4s]", getIdVendedor(), getIdProduto(), getQuantidade() );
    }
}
