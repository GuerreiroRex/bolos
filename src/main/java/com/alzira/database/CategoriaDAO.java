package com.alzira.database;

import com.alzira.model.CategoriaModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAO {
    public void InserirCategoria(String nomeCategoria) throws InterruptedException, SQLException{
        Database.Conectar();

        try {
            Database.Executar("INSERT INTO categorias (NOMECATEGORIA) VALUES ('" + nomeCategoria + "')");
        } catch (Exception e) {
            System.out.println("CategoriaDAO" + e);
        }
    }
    
    public List execSelect() throws JsonProcessingException, SQLException, InterruptedException {

        List<CategoriaModel> lista = new ArrayList<>();

        Database.Conectar();
        ResultSet resultado = Database.Ler("SELECT * FROM categorias");

        String db_nomeCategoria = null;

        while (resultado.next()) {
            db_nomeCategoria = resultado.getString("nomecategoria");
    
            lista.add(new CategoriaModel(db_nomeCategoria));
        }
        
        Database.Desconectar();
        
        return lista;
    }
    
    public void excluirCategoria(Integer id_cat) throws InterruptedException, SQLException{
        Database.Conectar();

        try {
            Database.Executar("DELETE FROM CATEGORIAS WHERE CATEGORIAID = " + id_cat);
        } catch (Exception e) {
            System.out.println("CategoriaDAO excluir" + e);
        }
    }
    
    public void alterarCategoria(String nomeCategoria, Integer id_categoria) throws InterruptedException, SQLException{
        Database.Conectar();
        
        try{
            Database.Executar("UPDATE CATEGORIAS SET NOMECATEGORIA = '" + nomeCategoria + "' WHERE CATEGORIAID = '" + id_categoria + "' ");
        } catch (Exception e) {
            System.out.println("CategoriaDAO alterar" + e);
        }
    }
}