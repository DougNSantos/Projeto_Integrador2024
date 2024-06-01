package cadastroepi;

import javax.swing.JOptionPane;
import tela.FrmPrincipal;
import java.sql.*;

public class CadastroEPI {
    
    static Connection conexao = null;

    public static void main(String[] args) {
        //new FrmPrincipal().setVisible(true);
        
        if(abrirConexao()){ //por padrão, se não usar descrever a condição, ele vai fazer um if(parametro)==true!!!
            //aqui estou dizendo, desvie o fluxo para o método abrirConexao()
            new FrmPrincipal().setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "Conexão não efetuada com BD");
        }
    }
    
    private static boolean abrirConexao(){
        try{ //O TRY{} NOS DEVOLVE UMA EXCEÇÃO SE NÃO CONSEGUIR REALIZAR A OPERAÇÃO, POR ISSO É USADO EM CONJUNTO:TRY-CATCH, SE ERRO ELE VAI LANÇAR UMA EXCEÇÃO(TROWN EXCEPTION) O TRY VAI CAPTURAR ESSSE LANÇAMENTO
            String dbdriver = "org.postgresql.Driver";
            String dburl = "jdbc:postgresql://localhost:5432/CadastroEPI";
            String dbuser = "postgres";
            String dbsenha = "postgres";
            
            //CARREGA DRIVER DO BANCO DE DADOS
            Class.forName(dbdriver);
            
            if (dbuser.length()!=0) //CONEXÃO COM USUÁRIO E SENHA
            {
                conexao = DriverManager.getConnection(dburl,dbuser,dbsenha);
                
            }
            else //CONEXÃO SEM USUÁRIO E SENHA
            {
                conexao = DriverManager.getConnection(dburl);
            }
            
            return true; //SE DEU CERTO, RETURN TRUE
            
        } catch (Exception e) {
            System.err.println("Erro ao tentar conectar: " + e);
            return false;
        }
        
    }
    
}
