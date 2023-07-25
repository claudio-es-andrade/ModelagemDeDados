package com.claudioesandradeecommerce.ecommercemaven;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {
    private final StringProperty id;
    private final StringProperty nome;
    private final StringProperty endereco;
    private final StringProperty cpf;
    private final StringProperty cnpj;

    public Cliente() {
        id       = new SimpleStringProperty(this, "id");
        nome     = new SimpleStringProperty(this, "nome");
        endereco = new SimpleStringProperty(this, "endereco");
        cpf      = new SimpleStringProperty(this, "cpf");
        cnpj     = new SimpleStringProperty(this, "cnpj");

    }
    // id property
    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId);}

    // nome property
    public StringProperty nomeProperty() { return nome; }
    public String getNome() { return nome.get(); }
    public void setNome(String newNome) { nome.set(newNome);}

    // endereco property
    public StringProperty enderecoProperty() { return endereco; }
    public String getEndereco() { return endereco.get(); }
    public void setEndereco(String newEndereco) { endereco.set(newEndereco);}

    // cpf property
    public StringProperty cpfProperty() { return cpf; }
    public String getCpf() { return cpf.get(); }
    public void setCpf(String newCpf) { cpf.set(newCpf);}

    // cnpj́ property
    public StringProperty cnpjProperty() { return cnpj; }
    public String getCnpj() { return cnpj.get(); }
    public void setCnpj(String newCnpj) { cnpj.set(newCnpj);}

    public String toString() {
        return String.format("[id=4s , nome=255s, endereco=255s, cpf=11s, cnpj=14]", getId(), getNome(), getEndereco(), getCpf(), getCnpj() );
    }


}
