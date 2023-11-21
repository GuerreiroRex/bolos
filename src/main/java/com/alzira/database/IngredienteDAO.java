package com.alzira.database;

import com.alzira.model.CategoriaModel;
import com.alzira.model.IngredienteModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class IngredienteDAO {
    public void inserirIngrediente(String nomeIngrediente, Double precoUnit) throws InterruptedException, SQLException{
        Database.Conectar();

        try {
            Database.Executar("INSERT INTO INGREDIENTES  (NOMEINGREDIENTE, PRECOUNITARIO) VALUES ('" + nomeIngrediente + "', '" + precoUnit + "')");
        } catch (Exception e) {
            System.out.println("IngredienteDAO" + e);
        }
    }

    public List execSelect() throws JsonProcessingException, SQLException, InterruptedException {

        List<IngredienteModel> lista = new ArrayList<>();

        Database.Conectar();
        ResultSet resultado = Database.Ler("SELECT * FROM ingredientes");

        String db_nomeIngrediente = null;
        Double db_precoUnit = null;

        while (resultado.next()) {
            db_nomeIngrediente = resultado.getString("nomeingrediente");
            db_precoUnit = resultado.getDouble("precounitario");
    
            lista.add(new IngredienteModel(db_nomeIngrediente, db_precoUnit));
        }
        
        Database.Desconectar();
        
        return lista;
    }

    public void excluirIngrediente(Integer id_ingred) throws InterruptedException, SQLException{
        Database.Conectar();

        try {
            Database.Executar("DELETE FROM INGREDIENTES WHERE INGREDIENTEID = " + id_ingred);
        } catch (Exception e) {
            System.out.println("IngredienteDAO excluir" + e);
        }
    }

    public void alterarIngrediente(String nomeIngred, Double precoUnit, Integer id_ingred) throws InterruptedException, SQLException{
        Database.Conectar();
        
        try{
            Database.Executar("UPDATE INGREDIENTES SET NOMEINGREDIENTE = '" + nomeIngred + "', PRECOUNITARIO = '" + precoUnit + "' WHERE INGREDIENTEID = '" + id_ingred + "' ");
        } catch (Exception e) {
            System.out.println("IngredienteDAO alterar" + e);
        }
    }    
}
