$("#formulario").on("submit", function(e) {
    e.preventDefault();

    $("#enviar").addClass("carregando");

    $("#enviar").toggleClass("btn-light");
    $("#enviar").toggleClass("btn-warning");

    Bridge.confirmarlogin($("#usuario").val(), $("#senha").val());
})