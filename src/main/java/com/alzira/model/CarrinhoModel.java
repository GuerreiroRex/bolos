package com.alzira.model;

import java.util.List;

public class CarrinhoModel {
    public Integer compraID;
    public List produto;
    public Double valorTotalCompra;

    public CarrinhoModel(Integer compraID, List produto,
    Double valorTotalCompra) {
        this.compraID = compraID;
        this.produto = produto;
        this.valorTotalCompra = valorTotalCompra;
    }

    public Integer getCompraID() {
        return compraID;
    }

    public void setCompraID(Integer compraID) {
        this.compraID = compraID;
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