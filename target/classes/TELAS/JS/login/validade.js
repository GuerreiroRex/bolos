function chamarInvalido() {
    $("#enviar").removeClass("carregando");
    
    $("#enviar").toggleClass("btn-warning");
    $("#enviar").toggleClass("btn-light");
    
    $("#usuario").addClass("erro");
}