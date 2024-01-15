package com.alzira.model;

public class ProdutoQuantidadeModel {
    private String id;
    private int quantidade;

    public ProdutoQuantidadeModel(String id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public String getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void incrementarQuantidade() {
        quantidade++;
    }

    public void decrementarQuantidade() {
        quantidade--;
    }
}