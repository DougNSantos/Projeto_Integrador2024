package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidades.usuario;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;

public class usuarioDAO implements IDAOT<usuario> {

    @Override
    public String salvar(usuario o) {
        try{ //MELHOR USAR TRY CATCH PARA FAZER INSERÇÃO EM BACOS DE DADOS!!!
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql= "insert into usuario "
                    + "values"
                    + "(default, '"+o.getNome()+"'"
                    + ", '"+o.getEmail()+"', md5('"+o.getSenha()+"'), 'a')" ;
                    
            
            System.out.println("SQL: " + sql);
            
            int retorno = st.executeUpdate(sql);
            
            return null;
        }
        catch(Exception e){
            System.out.println("Erro ao salvar novo Usuário: " + e);
            return e.toString();
        }
        
    }    

    @Override
    public String atualizar(usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<usuario> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<usuario> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public usuario consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean autenticar(usuario u) {

        try {
            String sql
                    = "SELECT email, senha "
                    + "FROM usuario "
                    + "WHERE "
                    + "email = '" + u.getEmail() + "'  and "
                    + "senha = md5 ('" + u.getSenha() + "') and "
                    + "situacao = 'a'";

            System.out.println("SQL: " + sql);

            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultadoQ.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao autenticar usuário: " + e);
            return false;
        }
    }

}
