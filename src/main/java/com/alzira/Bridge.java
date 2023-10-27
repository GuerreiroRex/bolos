package com.alzira;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.alzira.database.Database;
import javafx.scene.web.WebEngine;

public class Bridge {
    private static WebEngine webEngine;

    public Bridge(WebEngine motor) {
        webEngine = motor;
    }

    public void confirmarlogin(String usuario, String senha) throws IOException, SQLException, ClassNotFoundException {
        Database.Conectar();
        ResultSet restultado = Database.Ler("SELECT * FROM usuarios");

        String db_username = null;
        String db_senha = null;

        while (restultado.next()) {
            db_username = restultado.getString("username");
            db_senha = restultado.getString("senha");

            //System.err.println();

            if (db_username.equals(usuario) & db_senha.equals(senha)) {
                GUI.trocarTela("menu");
            }
        }
        Database.Desconectar();

        /*
         * else {
         * webEngine.executeScript("chamarInvalido()");
         * }
         */
    }
}