package com.alzira;

import com.alzira.database.CategoriaDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alzira.database.ProdutoDAO;
import com.alzira.database.UsuarioDAO;
import com.alzira.model.CategoriaModel;
import com.alzira.model.ProdutoModel;
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

    // ***********************
    public void adicionar_carrinho(String id) throws SQLException, InterruptedException {
        Carrinho.adicionarProduto(id);
    }

    public void remover_carrinho(String id) throws SQLException, InterruptedException {
        Carrinho.removerProduto(id);
    }
}
