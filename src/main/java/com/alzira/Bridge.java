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
        ResultSet resultado = Database.Ler("SELECT * FROM usuarios");

        String db_username = null;
        String db_senha = null;

        if (resultado.next()) {
           // Boolean validar = restultado.getBoolean(1);
            db_username = resultado.getString("username");
            db_senha = resultado.getString("senha");

            System.out.println(db_username + " " + db_senha);
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
}