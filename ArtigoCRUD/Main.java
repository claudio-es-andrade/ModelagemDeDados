import crudeDAO.ContatoDAO;
import crudeModel.Contato;

import java.sql.SQLException;
import java.sql.Date;

import crudeDAO.ContatoDAO;
import crudeModel.Contato;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Novo Contato Criado

        Contato contato = new Contato();
        contato.setNome("Carlos Alberto Reis");
        contato.setIdade( 35 );
        contato.setEmail("carlosAlbertoReis@crude.com.br");
        contato.setDataCadastro(new Date(2021-12-15));
        //contato.setId(3);

        ContatoDAO contatoDAO = new ContatoDAO();

        // CREATE - Criando o primeiro contato
        //contatoDAO.save(contato);
        // READ/RETRIEVE - Listando os contatos
        //contatoDAO.getContatos();
        // UPDATE - Atualizando / modificando campos no contato
        //contatoDAO.update(contato);
        // DELETE - Apagando um contato da tabela pelo seu ID.
        //contatoDAO.deleteByID(3);

        System.out.println("Lista Atual de Contatos");
        for ( Contato c : contatoDAO.getContatos()) {
            System.out.println("Contato: " + c.getNome() );
        }

    }
}