package com.alzira;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    public static List<String> lista = new ArrayList<>();
    
    public static void adicionarProduto(String id) {
        lista.add(id);
    }

    public static void removerProduto(String id) {
        lista.remove(id);
    }

    public static void finalizar() {
        //
    }
}