/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidades.epi;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author dougl
 */
public class epiDAO implements IDAOT <epi> {

    @Override
    public String salvar(epi o) {
        try{ //MELHOR USAR TRY CATCH PARA FAZER INSERÇÃO EM BACOS DE DADOS!!!
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql= "insert into epi "
                    + "values"
                    + "(default, "
                    + "'" + o.getNome() + "')";
            
            System.out.println("SQL: " + sql);
            
            int retorno = st.executeUpdate(sql);
            
            return null;
        }
        catch(Exception e){
            System.out.println("Erro ao inserir EPI: " + e);
            return e.toString();
        }
        
    }
    

    @Override
    public String atualizar(epi o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<epi> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<epi> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public epi consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
      
}
