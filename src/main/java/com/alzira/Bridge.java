package com.alzira;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alzira.database.Database;
import com.alzira.model.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Bridge {
    public void confirmarlogin(String usuario, String senha) throws IOException, SQLException, ClassNotFoundException {
        Database.Conectar();
        ResultSet resultado = Database.Ler("SELECT * FROM usuarios");

        String db_username = null;
        String db_senha = null;

        if (resultado.next()) {
            // Esse método está todo errado, estou trazendo toda a tabela e verificando 1
            // por 1
            // Quem deveria retornar a validação é o próprio banco de dados, use
            // resultado.GetBoolean()
            db_username = resultado.getString("username");
            db_senha = resultado.getString("senha");
        }

        if (db_username.equals(usuario) & db_senha.equals(senha)) {
            GUI.trocarTela("menu");
        }
        Database.Desconectar();

        /*
         * else {
         * webEngine.executeScript("chamarInvalido()");
         * }
         */
    }

    public void acessar_cadastrousuario() throws IOException {
        GUI.trocarTela("cadastrousuario");
    }

    public String ler_usuarios() throws JsonProcessingException, SQLException {
        List<Usuario> lista = new ArrayList<>();

        Database.Conectar();
        ResultSet resultado = Database.Ler("SELECT * FROM usuarios");

        String db_username = null;
        String db_senha = null;

        while (resultado.next()) {
            db_username = resultado.getString("username");
            db_senha = resultado.getString("senha");

            lista.add(new Usuario(db_username, db_senha));
        }

        System.out.println(lista);

        Database.Desconectar();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(lista);

        // new String[]{"a", "b"}
        return json;
    }

    public void inserir_usuario(String username, String senha) throws SQLException {
        Database.Conectar();

        try {
            Database.Executar("INSERT INTO usuarios (username, senha) VALUES ('" + username + "', '" + senha + "')");
        } catch (Exception e) {
            System.out.println(e);
        }

        Database.Desconectar();
    }
}