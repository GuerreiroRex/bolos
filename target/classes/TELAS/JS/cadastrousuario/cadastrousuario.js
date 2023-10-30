
$("tbody").append(`<tr><td>1</td><td>2</td></tr>`)

try {
    Bridge.acessar_cadastrousuario()
    //Bridge.cadastrousuario_carregar();

    $("tbody").append(`<tr><td>3</td><td>4</td></tr>`)

    //$("#tabela > tbody:last-child").append(`<tr><td>${a[0]}</td><td>${a[1]}</td></tr>`)

} catch (error) {
    $("body").append(`<p>${error}</p>`)
}