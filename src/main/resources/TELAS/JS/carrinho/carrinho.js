$(window).ready(function () {
    let item = JSON.parse(Bridge.ler_carrinho())[0];    

    item.produto.forEach(elemento => {
        $("#body").append(elemento.nomeProduto)
        $("#body").append(elemento.precoUnitarioProduto)
        $("#body").append(elemento.valorTotalProduto)

        let linha = 
        `
        <tr>
            <td scope="row">${elemento.nomeProduto}</td>
            <td>${elemento.precoUnitarioProduto}</td>
            <td>${elemento.valorTotalProduto}</td>
        </tr>
        `;

        $("#corpoCarrinho").append($.parseHTML(linha));
    });

    $("#preçoTotal").text("Total: " + item.valorTotalCompra)
})

function ConfirmarCompra() {
    Bridge.confirmar_carrinho($("#formaPagamento").val())
}

function irMenu() {
    Bridge.acessar_menu();
}