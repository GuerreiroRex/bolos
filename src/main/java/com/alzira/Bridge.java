package com.alzira;

import java.io.IOException;

import javafx.scene.web.WebEngine;

public class Bridge {
    private static WebEngine webEngine;

    public Bridge(WebEngine motor) {
        webEngine = motor;
    }

    public void confirmarlogin(String usuario, String senha) throws IOException {

        //Coloque o codigo de validar aqui, pode apagar porque isso não é útil
        System.out.println(usuario + " e " + senha);

        if (true) /* Sua condicional para ver se o usuário e senha bate */ {
            GUI.trocarTela("menu");
        } else {
            webEngine.executeScript("chamarInvalido()"); /* Chama no front-end a função chamar invalido */
        }
    }
}
