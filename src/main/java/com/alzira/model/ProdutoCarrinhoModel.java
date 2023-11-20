package com.alzira.model;

public class ProdutoCarrinhoModel {
    public String nomeProduto;
    public Integer quantidadeProduto;
    public Double precoUnitarioProduto;
    public Double valorTotalProduto;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Integer quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Double getPrecoUnitarioProduto() {
        return precoUnitarioProduto;
    }

    public void setPrecoUnitarioProduto(Double precoUnitarioProduto) {
        this.precoUnitarioProduto = precoUnitarioProduto;
    }

    public Double getValorTotalProduto() {
        return valorTotalProduto;
    }

    public void setValorTotalProduto(Double valorTotalProduto) {
        this.valorTotalProduto = valorTotalProduto;
    }

    public ProdutoCarrinhoModel(String c_nomeProduto, Integer c_quantidadeProduto, Double c_precoUnitarioProduto, Double c_valorTotalProduto) {
        this.nomeProduto = c_nomeProduto;
        this.quantidadeProduto = c_quantidadeProduto;
        this.precoUnitarioProduto = c_precoUnitarioProduto;
        this.valorTotalProduto = c_valorTotalProduto;
    }
}