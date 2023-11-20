let num_pagina = 1;
let por_pagina = 2;
let lista;

function montarListaIngrediente(ingredientes) {
    let linhas = "";

    ingredientes.forEach((nome) => {
        linhas +=
            `
        <tr>
            <td scope="row">${nome}</td>
        </tr>

        `;
    });

    let str =
        `
    <table class="table tabela-ingr">
        <tbody>
            ${linhas}
        </tbody>
    </table>
    `

    return str;
}

function montarModalIngrediente(elemento) {
    let str =
        `
    <button type="button" class="btn btn-primary botao" data-bs-toggle="modal" data-bs-target="#exampleModal-${elemento.produtoID}">
        Ingredientes
    </button>

    <div class="modal fade" id="exampleModal-${elemento.produtoID}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                <h5 class="modal-title">Nome dos Ingredientes</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    ${montarListaIngrediente(elemento.nomeIngrediente)}
                </div>
            </div>
        </div>
    </div>
    `

    return $.parseHTML(str);
}

function montarBarraControle(elemento) {
    let barraControle = document.createElement("div");
    let adicionar = document.createElement("button");
    let remover = document.createElement("button");

    let ingr = montarModalIngrediente(elemento);

    adicionar.innerHTML = "+";
    remover.innerHTML = "-";

    barraControle.classList.add("barra-controle");
    adicionar.classList.add("barra-adicionar");
    adicionar.classList.add("btn-success");
    remover.classList.add("barra-remover");
    remover.classList.add("btn-danger");

    adicionar.addEventListener("click", () => {
        Bridge.adicionar_carrinho(elemento.produtoID.toString());
    });

    remover.addEventListener("click", () => {
        Bridge.remover_carrinho(elemento.produtoID.toString());
    });

    const listaControle = [adicionar, ingr, remover];

    listaControle.forEach(item => {
        $(barraControle).append(
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
        let barraControle = montarBarraControle(elemento);



        titulo.innerHTML = elemento.nomeProduto;
        preco.innerHTML = "R$ " + elemento.precoFinal;

        titulo.classList.add("produto-titulo");
        preco.classList.add("produto-preco");

        const itens = [titulo, preco, barraControle];
        itens.forEach(item => {
            $(divisor).append(
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
