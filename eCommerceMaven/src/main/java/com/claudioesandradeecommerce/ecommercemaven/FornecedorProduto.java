package com.claudioesandradeecommerce.ecommercemaven;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FornecedorProduto {
    private final StringProperty idFornecedor;
    private final StringProperty idProduto;
    private final StringProperty quantidade;


    public FornecedorProduto() {
        idFornecedor               = new SimpleStringProperty(this, "idFornecedor");
        idProduto                  = new SimpleStringProperty(this, "idProduto");
        quantidade                 = new SimpleStringProperty(this, "quantidade");
    }
    // idFornecedor property
    public StringProperty idFornecedor() { return idFornecedor; }
    public String getIdFornecedor() { return idFornecedor.get(); }
    public void setIdFornecedor(String newIdFornecedor) { idFornecedor.set(newIdFornecedor);}

    // idProduto property
    public StringProperty idProduto() { return idProduto; }
    public String getIdProduto() { return idProduto.get(); }
    public void setIdProduto(String newIdProduto) { idProduto.set(newIdProduto);}

    // quantidade property
    public StringProperty quantidadeProperty() { return quantidade; }
    public String getQuantidade() { return quantidade.get(); }
    public void setQuantidade(String newQuantidade) { quantidade.set(newQuantidade);}

        public String toString() {
        return String.format("4s[idFornecedor=4s , idProduto=4s]", getIdFornecedor(), getIdProduto(), getQuantidade()  );
    }


}
