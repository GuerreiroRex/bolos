package com.alzira;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alzira.database.CategoriaDAO;
import com.alzira.database.IngredienteDAO;
import com.alzira.database.ProdutoDAO;
import com.alzira.database.UsuarioDAO;
import com.alzira.model.CategoriaModel;
import com.alzira.model.ProdutoModel;
import com.alzira.model.CarrinhoModel;
import com.alzira.model.IngredienteModel;
import com.alzira.model.Usuario;
import com.alzira.task.loginTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.web.WebEngine;

public class Bridge {
    private static WebEngine webEngine;

    public Bridge(WebEngine web) {
        webEngine = web;
    }

    // *********************** LOGIN
    public void confirmarlogin(String usuario, String senha) throws InterruptedException, SQLException, IOException {
        Thread thread = new Thread(new loginTask(usuario, senha));
        thread.start();
    }

    public static void invalidarLogin() {
        webEngine.executeScript("chamarInvalido()");
    }

    // *********************** USUARIO
    public void acessar_cadastrousuario() throws IOException {
        GUI.trocarTela("cadastrousuario");
    }

    public void acessar_carrinho() throws IOException {
        GUI.trocarTela("carrinho");
    }

    public void acessar_menu() throws IOException {
        GUI.trocarTela("menu");
    }

    public String ler_usuarios() throws JsonProcessingException, SQLException, InterruptedException {
        List<Usuario> lista = new ArrayList<>();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        lista = usuarioDAO.execSelect();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(lista);

        return json;
    }

    public void inserir_usuario(String username, String senha) throws SQLException, InterruptedException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.InserirUsuario(username, senha);
    }

    // *********************** PRODUTO
    public String ler_produtos() throws JsonProcessingException, SQLException, InterruptedException {
        List<ProdutoModel> lista = new ArrayList<>();

        ProdutoDAO produtoDAO = new ProdutoDAO();
        lista = produtoDAO.execSelect();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(lista);

        return json;
    }

    public void inserir_produto(String nomeProduto, Integer categoriaID, Double custoIngredientes, Double margemLucro,
            Double precoFinal) throws SQLException, InterruptedException {

        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.InserirProduto(nomeProduto, categoriaID, custoIngredientes, margemLucro, precoFinal);
    }

    public void excluir_produto(Integer id_prod) throws InterruptedException, SQLException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.excluirProduto(id_prod);
    }

    public void alterar_produto(String nomeProd, Integer id_categoria, Double custoIngred, Double margemLucro,
            Double precoFinal, Integer id) throws InterruptedException, SQLException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.alterarProduto(nomeProd, id_categoria, custoIngred, margemLucro, precoFinal, id);
    }

    // *********************** CATEGORIA
    public String ler_categorias() throws JsonProcessingException, SQLException, InterruptedException {
        List<CategoriaModel> lista = new ArrayList<>();

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        lista = categoriaDAO.execSelect();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(lista);

        return json;
    }

    public void inserir_categoria(String nomeCategoria) throws SQLException, InterruptedException {

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.InserirCategoria(nomeCategoria);
    }

    public void excluir_categoria(Integer id_cat) throws InterruptedException, SQLException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.excluirCategoria(id_cat);
    }

    public void alterar_categoria(String nome_cat, Integer id_cat) throws InterruptedException, SQLException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.alterarCategoria(nome_cat, id_cat);
    }

    // *********************** INGREDIENTE
    public String ler_ingredientes() throws JsonProcessingException, SQLException, InterruptedException {
        List<IngredienteModel> lista = new ArrayList<>();

        IngredienteDAO ingredienteDAO = new IngredienteDAO();
        lista = ingredienteDAO.execSelect();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(lista);

        return json;
    }

    public void inserir_ingrediente(String nomeIngred, Double precoUnit) throws SQLException, InterruptedException {

        IngredienteDAO ingredienteDAO = new IngredienteDAO();
        ingredienteDAO.inserirIngrediente(nomeIngred, precoUnit);
    }

    public void excluir_ingrediente(Integer id_ingred) throws InterruptedException, SQLException {
        IngredienteDAO ingredienteDAO = new IngredienteDAO();
        ingredienteDAO.excluirIngrediente(id_ingred);
    }

    public void alterar_ingrediente(String nome_ingred, Double precoUnit, Integer id_ingred)
            throws InterruptedException, SQLException {
        IngredienteDAO ingredienteDAO = new IngredienteDAO();
        ingredienteDAO.alterarIngrediente(nome_ingred, precoUnit, id_ingred);
    }

    // *********************** CARRINHO
    public void adicionar_carrinho(String id) throws SQLException, InterruptedException {
        Carrinho.adicionarProduto(id);
    }

    public void remover_carrinho(String id) throws SQLException, InterruptedException {
        Carrinho.removerProduto(id);
    }

    public String ler_carrinho() throws Exception {

        List<CarrinhoModel> lista = new ArrayList<>();

        lista = Carrinho.lerCarrinho();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(lista);

        return json;
    }

    public void confirmar_carrinho(String formaPagamento) {
        // ..
    }
}