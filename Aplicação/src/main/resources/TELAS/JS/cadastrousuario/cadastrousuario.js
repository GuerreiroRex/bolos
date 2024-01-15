$(window).ready(function () {
    let lista = JSON.parse(Bridge.ler_usuarios());

    lista.forEach(elemento => {
        $("tbody").append(`<tr><td>${elemento.username}</td><td>${elemento.senha}</td></tr>`)
    });
})

function inserir_usuario() {
    Bridge.inserir_usuario($("#iuser").val(), $("#isenha").val());
    window.location.reload();
}