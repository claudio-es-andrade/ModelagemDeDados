package crudeModel;
import java.util.Date;
import java.util.Objects;

public class Contato {
    private int id;
    private String nome;
    private int idade;
    private String email;
    private Date dataCadastro;

    public Contato() {
    }

    public Contato(int id, String nome, int idade, String email, Date dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.dataCadastro = dataCadastro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return getId() == contato.getId() && getIdade() == contato.getIdade() && Objects.equals(getNome(), contato.getNome()) && Objects.equals(getEmail(), contato.getEmail()) && Objects.equals(getDataCadastro(), contato.getDataCadastro());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getIdade(), getEmail(), getDataCadastro());
    }

    @Override
    public String toString() {
        return "Contato {" +
                " id= " + id +
                ", nome= '" + nome + '\'' +
                ", idade= " + idade +
                ", email= '" + email + '\'' +
                ", dataInclusao= " + dataCadastro +
                '}';
    }


}