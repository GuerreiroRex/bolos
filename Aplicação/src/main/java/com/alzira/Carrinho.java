package com.alzira;

import java.util.ArrayList;
import java.util.List;

import com.alzira.database.ProdutoDAO;
import com.alzira.model.CarrinhoModel;
import com.alzira.model.ProdutoCarrinhoModel;
import com.alzira.model.ProdutoQuantidadeModel;

public class Carrinho {
    public static List<ProdutoQuantidadeModel> lista = new ArrayList<>();
    
    public static void adicionarProduto(String id) {

        for (ProdutoQuantidadeModel produto : lista) {
            if (produto.getId().equals(id)) {
                // Se o id já estiver na lista, incrementa a quantidade e sai do método
                produto.incrementarQuantidade();
                return;
            }
        }

        // Se o id não estiver na lista, adiciona um novo ProdutoQuantidadeModel
        lista.add(new ProdutoQuantidadeModel(id, 1));
    }

    public static void removerProduto(String id) {

        for (ProdutoQuantidadeModel produto : lista) {
            if (produto.getId().equals(id)) {
                produto.decrementarQuantidade();

                // Se a quantidade chegar a zero, remova o produto da lista
                if (produto.getQuantidade() == 0) {
                    lista.remove(produto);
                }
                return;
            }
        }
    }

    public static List<CarrinhoModel> lerCarrinho() throws Exception {
        List<CarrinhoModel> carrinho = new ArrayList<>();
        List<ProdutoCarrinhoModel> listaProdutos = new ArrayList<>();

        Double valorTotalCompra = 0.00;

        // Percorre a lista e soma os valores
        for (ProdutoQuantidadeModel produto : lista) {

            Integer idProduto = Integer.parseInt(produto.getId());

            List<ProdutoCarrinhoModel> dadosProduto = ProdutoDAO.consultaProduto(idProduto);

            for (ProdutoCarrinhoModel item : dadosProduto) {

                item.setValorTotalProduto(produto.getQuantidade() * item.precoUnitarioProduto);

                listaProdutos.add(item);
            }
        }

        for (ProdutoCarrinhoModel itemLista : listaProdutos) {
            valorTotalCompra += itemLista.getValorTotalProduto();
        }

        carrinho.add(new CarrinhoModel(listaProdutos, valorTotalCompra));

        return carrinho;
    }

    public static void finalizar() {
        //
    }
}