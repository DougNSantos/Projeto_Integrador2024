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
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
                    + "(default, '" +o.getNome()+"', "+o.getCategoria()+")";
                    
            
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
        try{ //MELHOR USAR TRY CATCH PARA FAZER INSERÇÃO EM BACOS DE DADOS!!!
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "UPDATE epi " +
             "SET nome = '" + o.getNome() + "', " +
             "categoria = " + o.getCategoria() + " " +
             "WHERE id = " + o.getId();

                    
            
            System.out.println("SQL: " + sql);
            
            int retorno = st.executeUpdate(sql);
            
            return null;
        }
        catch(Exception e){
            System.out.println("Erro ao atualizar EPI: " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try{ //MELHOR USAR TRY CATCH PARA FAZER INSERÇÃO EM BACOS DE DADOS!!!
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql= "delete from epi "
                    + "where id = " + id;
                    
            
            System.out.println("SQL: " + sql);
            
            int retorno = st.executeUpdate(sql);
            
            return null;
        }
        catch(Exception e){
            System.out.println("Erro ao excluir EPI: " + e);
            return e.toString();
        }
    }

    @Override
    public ArrayList<epi> consultarTodos() {
        
        ArrayList<epi> epis = new ArrayList();
        
        try{ 
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql= ""
                    +"select *" //mais legivel no código: primeiro o que mostrar
                    +"from epi"; //segundo, de onde mostrar
                                   //terceiro se tiver, como mostrar
            
            System.out.println("SQL: " + sql);
            
            ResultSet retorno = st.executeQuery(sql);
            
            while(retorno.next()){
                epi Epi = new epi();
                
                Epi.setId(retorno.getInt("id"));
                Epi.setNome(retorno.getString("nome"));
                
                epis.add(Epi);
            }
        }
        catch(Exception e){
            System.out.println("Erro ao inserir EPI: " + e);            
        }
        
        return epis;
        
    }

    @Override
    public ArrayList<epi> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public epi consultarId(int id) {
        
        epi Epi = new epi();
        
        try{ 
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql= ""
                    +"select *" 
                    +"from epi "
                    +"where id = " + id; 
                                   
            
            System.out.println("SQL: " + sql);
            
            ResultSet retorno = st.executeQuery(sql);
            
            while(retorno.next()){
                Epi = new epi();
                
                Epi.setId(retorno.getInt("id"));
                Epi.setNome(retorno.getString("nome"));
                
                
            }
        }
        catch(Exception e){
            System.out.println("Erro ao consultar EPI: " + e);            
        }
        
        return Epi;
        
    }
    
    public void popularTabela(JTable tabela, String criterio) {
        
        ResultSet resultadoQ;
        
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "IDENTIFICADOR";
        cabecalho[1] = "NOME EPI";
        cabecalho[2] = "CATEGORIA";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM epi "
                    + "WHERE "
                    + "NOME ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][3];

        } catch (Exception e) {
            System.out.println("Erro ao consultar epis: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM EPI "
                    + "WHERE "
                    + "NOME ILIKE '%" + criterio + "%'"
                    + "ORDER BY id");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = resultadoQ.getInt("categoria");

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
//                    return ImageIcon.class;
                }
                return Object.class;
            }
        });

        // permite selecao de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(140);
                    break;
//                case 2:
//                    column.setPreferredWidth(14);
//                    break;
            }
        }
        // renderizacao das linhas da tabela = mudar a cor
//        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value,
//                    boolean isSelected, boolean hasFocus, int row, int column) {
//                super.getTableCellRendererComponent(table, value, isSelected,
//                        hasFocus, row, column);
//                if (row % 2 == 0) {
//                    setBackground(Color.GREEN);
//                } else {
//                    setBackground(Color.LIGHT_GRAY);
//                }
//                return this;
//            }
//        });
    }

}

    
      
