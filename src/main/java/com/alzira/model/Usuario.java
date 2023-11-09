package com.alzira.model;

public class Usuario {
    public String username;
    public String senha;

    public Usuario(String c_username, String c_senha) {
        username = c_username;
        senha = c_senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }    
}