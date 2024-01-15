package com.alzira.model;

public class ProdutoCarrinhoModel {
    public Integer idProduto;
    public String nomeProduto;
    public Double precoUnitarioProduto;
    public Double valorTotalProduto;

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }
    
    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    
    public Double getValorTotalProduto() {
        return valorTotalProduto;
    }

    public void setValorTotalProduto(Double valorTotalProduto) {
        this.valorTotalProduto = valorTotalProduto;
    }

    public Double getPrecoUnitarioProduto() {
        return precoUnitarioProduto;
    }

    public void setPrecoUnitarioProduto(Double precoUnitarioProduto) {
        this.precoUnitarioProduto = precoUnitarioProduto;
    }

    public ProdutoCarrinhoModel(Integer c_idProduto, String c_nomeProduto, Double c_precoUnitario) {
        this.idProduto = c_idProduto;
        this.nomeProduto = c_nomeProduto;
        this.precoUnitarioProduto = c_precoUnitario;
    }
}