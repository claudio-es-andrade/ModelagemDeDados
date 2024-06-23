package crudeDAO;

import crudeDataFactory.ConnectionFactory;
import crudeModel.Contato;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import static crudeDataFactory.ConnectionFactory.createConnectionToMySQL;

public class ContatoDAO {

    /*
        CRUD
        CREATE
        READ/RETRIEVE
        UPDATE
        DELETE
     */

    public void save(Contato contato) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO contatos(nome, idade, email,dataCadastro) VALUES(?,?,?,?)";

        Connection conn = null;
        JdbcPreparedStatement pstm = null;

        try {
            // Criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();
            // Criar uma PreparedStatement para executar a query
            pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

            // Adicionar os valores que são esperados pela query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setString(3, contato.getEmail());
            pstm.setDate(4, (Date) contato.getDataCadastro());

            // Executar a query
            pstm.execute();
            System.out.println("Contato salvo com sucesso");


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // Fechar as conexões
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    // Método Read/Retrieve
    public List<Contato> getContatos(){
        String sql = "SELECT * FROM contatos";

        List<Contato> contatos = new ArrayList<Contato>();

        Connection conn = null;
        JdbcPreparedStatement pstm = null;

        // Classe que recupera os dados do banco, SELECT * FROM
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Contato contato = new Contato();

                // Recuperar o id
                contato.setId(rset.getInt("id"));
                // Recuperar o nome
                contato.setNome(rset.getString("nome"));
                // Recuperar a idade
                contato.setIdade(rset.getInt("idade"));
                // Recuperar o email
                contato.setEmail(rset.getString("email"));
                // Recuperar a dataCadastro
                contato.setDataCadastro(rset.getDate("dataCadastro"));

                contatos.add(contato);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(rset != null) {
                    rset.close();
                }
                if(rset != null) {
                    pstm.close();
                }
                if(conn != null) {
                    conn.close();
                }
            }catch(Exception e){
                e.printStackTrace();

            }
            return contatos;
        }
    }
    // Método Update
    public void update(Contato contato) {
        String sql = "UPDATE contatos SET nome = ?, idade = ?, email = ?, dataCadastro = ? WHERE id = ? ";

        Connection conn = null;
        JdbcPreparedStatement pstm = null;

        try {
            // Criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();
            // Criar uma PreparedStatement para executar a query
            pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

            // Adicionar os valores que são esperados pela query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setString(3, contato.getEmail());
            pstm.setDate(4, (Date) contato.getDataCadastro());
            //WHERE part
            pstm.setInt(5, contato.getId());

            // Executar a query
            pstm.execute();
            System.out.println("Contato atualizado com sucesso");


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // Fechar as conexões
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    // Método Delete
    public void deleteByID(int id) {
        String sql = "DELETE FROM contatos WHERE id = ? ";

        Connection conn = null;
        JdbcPreparedStatement pstm = null;

        try {
            // Criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();
            // Criar uma PreparedStatement para executar a query
            pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

            // Deletar os valores que são esperados pela query
            //WHERE part
            pstm.setInt(1, id);

            // Executar a query
            pstm.execute();
            System.out.println("Contato No." + id + " removido com sucesso");


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // Fechar as conexões
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }


}
