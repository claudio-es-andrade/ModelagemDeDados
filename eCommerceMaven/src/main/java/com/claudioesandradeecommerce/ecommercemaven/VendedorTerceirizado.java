package com.claudioesandradeecommerce.ecommercemaven;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VendedorTerceirizado {
    private final StringProperty id;
    private final StringProperty razaoSocial;
    private final StringProperty cnpj;
    private final StringProperty endereco;

    public VendedorTerceirizado() {
        id             = new SimpleStringProperty(this, "id");
        razaoSocial    = new SimpleStringProperty(this, "razaoSocial");
        cnpj           = new SimpleStringProperty(this, "cnpj");
        endereco       = new SimpleStringProperty(this, "endereco");
    }
    // id property
    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId);}
    // razaoSocial property
    public StringProperty razaoSocialProperty() { return razaoSocial; }
    public String getRazaoSocial() { return razaoSocial.get(); }
    public void setRazaoSocial(String newRazaoSocial) { razaoSocial.set(newRazaoSocial);}
    // cnpj́ property
    public StringProperty cnpjProperty() { return cnpj; }
    public String getCnpj() { return cnpj.get(); }
    public void setCnpj(String newCnpj) { cnpj.set(newCnpj);}
    // endereco property
    public StringProperty enderecoProperty() { return endereco; }
    public String getEndereco() { return endereco.get(); }
    public void setEndereco(String newEndereco) { endereco.set(newEndereco);}


    public String toString() {
        return String.format("4s[id=4s , RazaoSocial=100s]", getId(), getRazaoSocial(), getCnpj(), getEndereco() );
    }
}
