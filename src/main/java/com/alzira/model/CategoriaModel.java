package com.alzira.model;


public class CategoriaModel {
    public Integer categoriaID;
    public String NomeCategoria; 

    public CategoriaModel(String c_NomeCategoria) {
        this.NomeCategoria = c_NomeCategoria;
    }

    public Integer getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(Integer categoriaID) {
        this.categoriaID = categoriaID;
    }

    public String getNomeCategoria() {
        return NomeCategoria;
    }

    public void setNomeCategoria(String NomeCategoria) {
        this.NomeCategoria = NomeCategoria;
    }
}
