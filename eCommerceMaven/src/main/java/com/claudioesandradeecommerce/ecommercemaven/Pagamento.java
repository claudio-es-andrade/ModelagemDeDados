package com.claudioesandradeecommerce.ecommercemaven;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pagamento {
    private final StringProperty id;
    private final StringProperty identificacao;


    public Pagamento() {
        id               = new SimpleStringProperty(this, "id");
        identificacao    = new SimpleStringProperty(this, "cpf");


    }
    // id property
    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId);}
    // identificacao property
    public StringProperty identificacaoProperty() { return identificacao; }
    public String getIdentificacao() { return identificacao.get(); }
    public void setIdentificacao(String newIdentificacao) { identificacao.set(newIdentificacao);}

    public String toString() {
        return String.format("4s[id=4s , identificacao=20s]", getId(), getIdentificacao()  );
    }


}
