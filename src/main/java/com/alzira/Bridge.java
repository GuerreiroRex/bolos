package com.alzira;

import java.io.IOException;

public class Bridge {
    public void confirmarlogin(String usuario, String senha) throws IOException {

        //Coloque o codigo de validar aqui, pode apagar porque isso não é útil
        System.out.println(usuario + " e " + senha);

        if (true) /* Sua condicional para ver se o usuário e senha bate */ {
            GUI.trocarTela("menu");
        }
    }
}
