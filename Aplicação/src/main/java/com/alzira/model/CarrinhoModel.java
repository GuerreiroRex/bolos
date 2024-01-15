package com.alzira.model;

import java.util.List;

public class CarrinhoModel {
    public List produto;
    public Double valorTotalCompra;

    public CarrinhoModel(List produto, Double valorTotalCompra) {
        this.produto = produto;
        this.valorTotalCompra = valorTotalCompra;
    }

    public List getProduto() {
        return produto;
    }

    public void setProduto(List produto) {
        this.produto = produto;
    }

    public Double getValorTotalCompra() {
        return valorTotalCompra;
    }

    public void setValorTotalCompra(Double valorTotalCompra) {
        this.valorTotalCompra = valorTotalCompra;
    }
}