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
                System.out.println("Login incorreto");
                Bridge.invalidarLogin();
            }
            
        });
    }

    @Override
    protected Void call() throws Exception {
        Database.Conectar();
        ResultSet resultado = Database.Ler("SELECT * FROM usuarios");

        String db_username = "null";
        String db_senha = "null";

        if (resultado.next()) {
            // Esse método está todo errado, estou trazendo toda a tabela e verificando 1
            // por 1
            // Quem deveria retornar a validação é o próprio banco de dados, use
            // resultado.GetBoolean()
            db_username = resultado.getString("username");
            db_senha = resultado.getString("senha");
        }
        Database.Desconectar();

        if (!(db_username.equals(usuario) & db_senha.equals(senha))) {
            System.out.println("Falha 1");
            throw new Exception("usuario ou senha incorretos");
        }
        return null;
    }

}
