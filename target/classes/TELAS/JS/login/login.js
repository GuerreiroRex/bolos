function confirmarlogin() {
    let usuario = $("#usuario").val()
    
    Bridge.confirmarlogin($("#usuario").val(), $("#senha").val());
}

