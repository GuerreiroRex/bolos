/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alzira.model;

/**
 *
 * @author Renanzed
 */
public class ProdutoModel {
    public Integer ID;
    public String nomeProduto;
    public Integer categoriaID;
    public Double custoIngredientes;
    public Double margemLucro;
    public Double precoFinal;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
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

    public ProdutoModel(String c_nomeProduto, Integer c_categoriaID, Double c_custoIngredientes, Double c_margemLucro, Double c_precoFinal) {
        this.nomeProduto = c_nomeProduto;
        this.categoriaID = c_categoriaID;
        this.custoIngredientes = c_custoIngredientes;
        this.margemLucro = c_margemLucro;
        this.precoFinal = c_precoFinal;
    }

}
