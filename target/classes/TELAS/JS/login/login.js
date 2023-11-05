$("#formulario").on("submit", function(e) {
    e.preventDefault();

    $("#enviar").toggleClass("btn-light");
    $("#enviar").toggleClass("btn-warning");

    Bridge.confirmarlogin($("#usuario").val(), $("#senha").val());
})

