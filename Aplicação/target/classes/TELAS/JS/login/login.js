$("#formulario").on("submit", function(e) {
    e.preventDefault();
    $("#mensagem").text("")

    $("#enviar").toggleClass("btn-light");
    $("#enviar").toggleClass("btn-warning");

    Bridge.confirmarlogin($("#usuario").val(), $("#senha").val());
})

