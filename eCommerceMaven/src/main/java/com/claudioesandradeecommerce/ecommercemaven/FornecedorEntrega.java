package com.claudioesandradeecommerce.ecommercemaven;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FornecedorEntrega {
    private final StringProperty idEntrega;
    private final StringProperty idFornecedor;
    private final StringProperty descricao;


    public FornecedorEntrega() {
        idEntrega                  = new SimpleStringProperty(this, "idEntrega");
        idFornecedor               = new SimpleStringProperty(this, "idFornecedor");
        descricao                  = new SimpleStringProperty(this, "descricao");
    }

    // idEntrega property
    public StringProperty idEntrega() { return idEntrega; }
    public String getIdEntrega() { return idEntrega.get(); }
    public void setIdEntrega(String newIdEntrega) { idEntrega.set(newIdEntrega);}

    // idFornecedor property
    public StringProperty idFornecedor() { return idFornecedor; }
    public String getIdFornecedor() { return idFornecedor.get(); }
    public void setIdFornecedor(String newIdFornecedor) { idFornecedor.set(newIdFornecedor);}

    // descricao property
    public StringProperty descricaoProperty() { return descricao; }
    public String getDescricao() { return descricao.get(); }
    public void setDescricao(String newDescricao) { descricao.set(newDescricao);}

        public String toString() {
        return String.format("4s[idEntrega=4s , idFornecedor=4s]", getIdEntrega(), getIdFornecedor(), getDescricao()  );
    }

}
