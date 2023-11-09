package com.alzira.task;

import java.io.IOException;
import java.sql.ResultSet;

import com.alzira.Bridge;
import com.alzira.GUI;
import com.alzira.database.Database;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public class loginTask extends Task<Void> {
    private final String usuario;
    private final String senha;

    public loginTask(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;

        this.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                try {
                    GUI.trocarTela("menu");
                } catch (IOException e) {
                    System.out.println("Erro em trocar a tela");
                }
            }
        });

        this.setOnFailed(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                Bridge.invalidarLogin();
            }

        });
    }

    @Override
    protected Void call() throws Exception {
        Boolean validade = false;
        Database.Conectar();
        ResultSet resultado = Database.Ler("SELECT * FROM login");

        String db_username = "null";
        String db_senha = "null";

        while (resultado.next()) {
            db_username = resultado.getString("username");
            db_senha = resultado.getString("senha");

            if ((db_username.equals(usuario) & db_senha.equals(senha))) {
                validade = true;
                break;
            }
        }

        if (!validade) {
            throw new Exception("Validação de usuário ou senha negativa.");
        }

        Database.Desconectar();

        return null;
    }
}