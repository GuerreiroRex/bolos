package com.alzira;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.alzira.database.Database;
import com.alzira.model.Usuario;
import com.alzira.task.loginTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.web.WebEngine;

public class Bridge {
    private static WebEngine webEngine;

    public Bridge(WebEngine web) {
        webEngine = web;
    }

    public void confirmarlogin(String usuario, String senha) throws InterruptedException, SQLException, IOException {
        Thread thread = new Thread(new loginTask(usuario, senha));
        thread.start();
    }

    public static void invalidarLogin() {
        webEngine.executeScript("teste()");
    }


    public void acessar_cadastrousuario() throws IOException {
        GUI.trocarTela("cadastrousuario");
    }

    public String ler_usuarios() throws JsonProcessingException, SQLException, InterruptedException {
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

    public void inserir_usuario(String username, String senha) throws SQLException, InterruptedException {
        Database.Conectar();

        try {
            Database.Executar("INSERT INTO usuarios (username, senha) VALUES ('" + username + "', '" + senha + "')");
        } catch (Exception e) {
            System.out.println(e);
        }

        Database.Desconectar();
    }
}