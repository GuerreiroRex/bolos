$("#formulario").on("submit", function(e) {
    e.preventDefault();

    $("#enviar").addClass("carregando");

    Bridge.confirmarlogin($("#usuario").val(), $("#senha").val());
})