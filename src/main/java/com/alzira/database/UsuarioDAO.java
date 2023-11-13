package com.alzira.database;

import com.alzira.model.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {
    
    public void InserirUsuario(String nome, String senha) throws InterruptedException, SQLException{
        Database.Conectar();

        try {
            Database.Executar("INSERT INTO login (username, senha) VALUES ('" + nome + "', '" + senha + "')");
        } catch (Exception e) {
            System.out.println("UsuarioDAO" + e);
        }
        //Database.Desconectar();
    }
    
        public List execSelect() throws JsonProcessingException, SQLException, InterruptedException {

            List<Usuario> lista = new ArrayList<>();

            Database.Conectar();
            ResultSet resultado = Database.Ler("SELECT * FROM login");

            String db_username = null;
            String db_senha = null;

            while (resultado.next()) {
                db_username = resultado.getString("username");
                db_senha = resultado.getString("senha");
    
                lista.add(new Usuario(db_username, db_senha));
            }
        
            Database.Desconectar();
        
            return lista;
    }
}
