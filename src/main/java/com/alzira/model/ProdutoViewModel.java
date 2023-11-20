package com.alzira.model;

import java.util.List;

public class ProdutoViewModel {
    public Integer produtoID;
    public String nomeProduto;
    public Integer categoriaID;
    public String nomeCategoria;
    public Double custoIngredientes;
    public Double margemLucro;
    public Double precoFinal;
    public List nomeIngrediente;

    public Integer getProdutoID() {
        return produtoID;
    }

    public void setProdutoID(Integer produtoID) {
        this.produtoID = produtoID;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(Integer categoriaID) {
        this.categoriaID = categoriaID;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public Double getCustoIngredientes() {
        return custoIngredientes;
    }

    public void setCustoIngredientes(Double custoIngredientes) {
        this.custoIngredientes = custoIngredientes;
    }

    public Double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public Double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(Double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public List getNomeIngrediente() {
        return nomeIngrediente;
    }

    public void setNomeIngrediente(List nomeIngrediente) {
        this.nomeIngrediente = nomeIngrediente;
    }

    public ProdutoViewModel(Integer c_produtoID, String c_nomeProduto, Integer c_categoriaID, String c_nomeCategoria, Double c_custoIngredientes, Double c_margemLucro, Double c_precoFinal, List c_nomeIngrediente) {
        this.produtoID = c_produtoID;
        this.nomeProduto = c_nomeProduto;
        this.categoriaID = c_categoriaID;
        this.nomeCategoria = c_nomeCategoria;
        this.custoIngredientes = c_custoIngredientes;
        this.margemLucro = c_margemLucro;
        this.precoFinal = c_precoFinal;
        this.nomeIngrediente = c_nomeIngrediente;
    }
}