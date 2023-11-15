package com.alzira;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    public static List<Integer> lista = new ArrayList<>();
    
    public static void adicionarProduto(Integer id) {
        lista.add(id);
    }

    public static void removerProduto(Integer id) {
        lista.remove(id);
    }

    public static void finalizar() {
        //
    }
}
