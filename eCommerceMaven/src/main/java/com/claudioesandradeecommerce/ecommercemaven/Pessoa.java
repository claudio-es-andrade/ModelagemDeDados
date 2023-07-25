package com.claudioesandradeecommerce.ecommercemaven;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pessoa {
    private final StringProperty id;
    private final StringProperty cpf;
    private final StringProperty cnpj;

    public Pessoa() {
        id      = new SimpleStringProperty(this, "id");
        cpf     = new SimpleStringProperty(this, "cpf");
        cnpj    = new SimpleStringProperty(this, "cnpj");

    }
    // id property
    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId);}
    // cpf property
    public StringProperty cpfProperty() { return cpf; }
    public String getCpf() { return cpf.get(); }
    public void setCpf(String newCpf) { cpf.set(newCpf);}
    // cnpj property
    public StringProperty cnpjProperty() { return cnpj; }
    public String getCnpj() { return cnpj.get(); }
    public void setCnpj(String newCnpj) { cnpj.set(newCnpj);}

    public String toString() {
        return String.format("4s[id=4s , cpf=11s]", getId(), getCpf(), getCnpj() );
    }


}
