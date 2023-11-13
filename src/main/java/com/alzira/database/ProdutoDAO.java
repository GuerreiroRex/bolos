package com.alzira.database;

import com.alzira.model.ProdutoModel;
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
    
    public List execSelect() throws JsonProcessingException, SQLException, InterruptedException {

        List<ProdutoModel> lista = new ArrayList<>();

        Database.Conectar();
        ResultSet resultado = Database.Ler("SELECT * FROM produtos");

        String db_nomeProduto = null;
        Integer db_categoriaID = null;
        Double db_custoIngrediente = null;
        Double db_margemLucro = null;
        Double db_precoFinal = null;

        while (resultado.next()) {
            db_nomeProduto = resultado.getString("nomeproduto");
            db_categoriaID = resultado.getInt("categoriaid");
            db_custoIngrediente = resultado.getDouble("custoingredientes");
            db_margemLucro = resultado.getDouble("margemlucro");
            db_precoFinal = resultado.getDouble("precofinal");
    
            lista.add(new ProdutoModel(db_nomeProduto, db_categoriaID, db_custoIngrediente, db_margemLucro, db_precoFinal));
        }
        
        Database.Desconectar();
        
        return lista;
    }  
}
