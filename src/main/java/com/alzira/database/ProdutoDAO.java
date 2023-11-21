package com.alzira.database;

import com.alzira.model.ProdutoCarrinhoModel;
import com.alzira.model.ProdutoModel;
import com.alzira.model.ProdutoViewModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDAO {
    
    public void InserirProduto(String nomeProduto, Integer categoriaID, Double custoIngredientes, Double margemLucro, Double precoFinal) throws InterruptedException, SQLException{
        Database.Conectar();

        try {
            Database.Executar("INSERT INTO produtos (nomeProduto, categoriaID, custoIngredientes, margemLucro, precoFinal) VALUES ('" + nomeProduto + "', '" + categoriaID + "', '" + custoIngredientes + "', '" + margemLucro + "', '" + precoFinal + "')");
        } catch (Exception e) {
            System.out.println("ProdutoDAO" + e);
        }
        //Database.Desconectar();
    }

   public static List<ProdutoCarrinhoModel> consultaProduto(Integer produtoId) throws Exception {

        List<ProdutoCarrinhoModel> lista = new ArrayList<>();

        Database.Conectar();
        ResultSet resultado = Database.Ler("SELECT * FROM PRODUTOS WHERE PRODUTOID = '" + produtoId + "'");
        
        Integer db_id = null;
        String db_nomeProduto = null;
        Double db_precoUnitario = null;

        while (resultado.next()) {            
            db_id = resultado.getInt("produtoid");          
            db_nomeProduto = resultado.getString("nomeproduto");
            db_precoUnitario = resultado.getDouble("precofinal");
                        
            lista.add(new ProdutoCarrinhoModel(db_id, db_nomeProduto, db_precoUnitario));
        }
        
        Database.Desconectar();
        
        return lista;
    }  

    public List execSelect() throws JsonProcessingException, SQLException, InterruptedException {

        List<ProdutoViewModel> lista = new ArrayList<>();
        //List<String> listaIngrediente = new ArrayList<>();
        List<Integer> listaID = new ArrayList<>(); //essa lista serve para comparar se o ID vindo do banco já foi inserido na lista que é retornada.

        Database.Conectar();
        ResultSet resultado = Database.Ler("SELECT * FROM VW_VISUALIZAPRODUTO");              

        Integer db_id = null;
        String db_nomeProduto = null;
        Integer db_categoriaID = null;
        String db_nomeCategoria = null;
        Double db_custoIngrediente = null;
        Double db_margemLucro = null;
        Double db_precoFinal = null;
        String db_nomeIngrediente = null;

        while (resultado.next()) {
            List<String> listaIngrediente = new ArrayList<>();
            
            db_id = resultado.getInt("produtoid");
            
            if(listaID.contains(db_id)){
                continue;
            }
            listaID.add(db_id);
            
            db_nomeProduto = resultado.getString("nomeproduto");
            db_categoriaID = resultado.getInt("categoriaid");
            db_nomeCategoria = resultado.getString("nomecategoria");
            db_custoIngrediente = resultado.getDouble("custoingredientes");
            db_margemLucro = resultado.getDouble("margemlucro");
            db_precoFinal = resultado.getDouble("precofinal");

            
            ResultSet resultadoAux = Database.Ler("SELECT * FROM VW_VISUALIZAPRODUTO WHERE PRODUTOID = " + db_id);            

            while(resultadoAux.next()){
                db_nomeIngrediente = resultadoAux.getString("nomeingrediente");
                listaIngrediente.add(db_nomeIngrediente);
            }
            
            lista.add(new ProdutoViewModel(db_id, db_nomeProduto, db_categoriaID,db_nomeCategoria, db_custoIngrediente, db_margemLucro, db_precoFinal, listaIngrediente));            
        }
        
        Database.Desconectar();
        
        return lista;
    }  
}