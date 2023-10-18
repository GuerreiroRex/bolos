$("#formulario").on("submit", function(e) {
    e.preventDefault();
    Bridge.confirmarlogin($("#usuario").val(), $("#senha").val());
})

