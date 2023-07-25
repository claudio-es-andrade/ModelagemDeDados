package com.claudioesandradeecommerce.ecommercemaven;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Nota {

    private final StringProperty nome;
    private final StringProperty cpf;
    private final StringProperty cnpj;
    private final StringProperty categoria;
    private final StringProperty situacao;
    private final StringProperty valor;
    private final StringProperty frete;

    public Nota() {

        nome       = new SimpleStringProperty(this, "nome");
        cpf        = new SimpleStringProperty(this, "cpf");
        cnpj       = new SimpleStringProperty(this, "cnpj");
        categoria  = new SimpleStringProperty(this, "categoria");
        situacao   = new SimpleStringProperty(this, "situacao");
        valor      = new SimpleStringProperty(this, "valor");
        frete      = new SimpleStringProperty(this, "frete");

    }

    // nome property
    public StringProperty nomeProperty() { return nome; }
    public String getNome() { return nome.get(); }
    public void setNome(String newNome) { nome.set(newNome);}

    // cpf property
    public StringProperty cpfProperty() { return cpf; }
    public String getCpf() { return cpf.get(); }
    public void setCpf(String newCpf) { cpf.set(newCpf);}

    // cnpj property
    public StringProperty cnpjProperty() { return cnpj; }
    public String getCnpj() { return cnpj.get(); }
    public void setCnpj(String newCnpj) { cnpj.set(newCnpj);}

    // categoria property
    public StringProperty categoriaProperty() { return categoria; }
    public String getCategoria() { return categoria.get(); }
    public void setCategoria(String newCategoria) { categoria.set(newCategoria);}

    // situacao property
    public StringProperty situacaoProperty() { return situacao; }
    public String getSituacao() { return situacao.get(); }
    public void setSituacao(String newSituacao) { situacao.set(newSituacao);}

    // valor property
    public StringProperty valorProperty() { return valor; }
    public String getValor() { return valor.get(); }
    public void setValor(String newValor) { valor.set(newValor);}

    // frete property
    public StringProperty freteProperty() { return frete; }
    public String getFrete() { return frete.get(); }
    public void setFrete(String newFrete) { frete.set(newFrete);}

    public String toString() {
        return String.format("[id=4s , nome=255s, cpf=11s, cnpj=14]", getNome(), getCpf(), getCnpj(), getCategoria(), getSituacao(), getValor(), getFrete() );
    }
}
