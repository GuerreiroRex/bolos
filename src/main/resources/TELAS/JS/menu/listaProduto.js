let num_pagina = 1;
let por_pagina = 6;
let lista;

function montarBarraControle(elemento) {
    let barraControle = document.createElement("div");
    let adicionar = document.createElement("button");
    let remover = document.createElement("button");

    adicionar.innerHTML = "+";
    remover.innerHTML = "-";

    barraControle.classList.add("barra-controle");
    adicionar.classList.add("barra-adicionar");
    remover.classList.add("barra-remover");

    adicionar.addEventListener("click", () => {
        Bridge.adicionar_carrinho(elemento.produtoID.toString());
    });

    remover.addEventListener("click", () => {
        Bridge.remover_carrinho(elemento.produtoID.toString());
    });

    const listaControle = [adicionar, remover];

    listaControle.forEach(item => {
        barraControle.appendChild(
            item
        );
    });

    return barraControle;
}

async function listar_produto(nova_lista) {
    nova_lista.forEach(elemento => {
        let divisor = document.createElement("div");
        divisor.classList.add("divisor-produto");

        let titulo = document.createElement("p");
        let preco = document.createElement("p");
        let barraControle = montarBarraControle(elemento);;


        titulo.innerHTML = elemento.nomeProduto;
        preco.innerHTML = "R$ " + elemento.precoFinal;

        titulo.classList.add("produto-titulo");
        preco.classList.add("produto-preco");

        const itens = [titulo, preco, barraControle];
        itens.forEach(item => {
            divisor.appendChild(
                item
            );
        });

        $("#container").append(divisor);
    });
}

function paginar() {
    nova_lista = lista.slice((num_pagina - 1) * por_pagina, num_pagina * por_pagina);

    $("#container").html("");    
    listar_produto(nova_lista).then();
}

function carregarMaisProdutos() {
    por_pagina = por_pagina + 2;
    paginar();
}

$(window).ready(function () {
    lista = JSON.parse(Bridge.ler_produtos());
    paginar();
});
