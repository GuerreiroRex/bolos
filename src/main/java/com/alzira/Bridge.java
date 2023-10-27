package com.alzira;

import java.io.IOException;
import java.sql.SQLException;
import com.alzira.database.Database;
import javafx.scene.web.WebEngine;

public class Bridge {
    private static WebEngine webEngine;

    public Bridge(WebEngine motor) {
        webEngine = motor;
    }   
    
    public void confirmarlogin(String usuario, String senha) throws IOException, SQLException, ClassNotFoundException {
        
        if (true) {
            GUI.trocarTela("menu");
        } /* else {
            webEngine.executeScript("chamarInvalido()");
        } */
    }
}