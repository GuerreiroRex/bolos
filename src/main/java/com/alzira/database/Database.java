package com.alzira.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static String jdbcURL = "jdbc:h2:file:./src/main/resources/DATABASE/database";
    // private static String backup = "X:\\Alunos\\081210040\\database";
    private static String username = "SA";
    private static String password = "1234";

    /*
     * Lembre de fazer esta classe funcionar de modo assincrono,
     * para evitar travamentos
     */

    private static Connection connection;

    public static Connection Conectar() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(jdbcURL, username, password);
        }

        return connection;
    }

    public static void Desconectar() throws SQLException {
        // connection.prepareStatement("BACKUP TO '" + backup +
        // ".zip'").executeUpdate();

        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    public static ResultSet Ler(String sql) throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e);
        }

        return resultSet;
    }

    public static void Executar(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    /*
     * Connection ab = Database.Conectar();
     * Database.
     * Executar("Create table usuarios (username varchar(256) primary key, senha varchar(256))"
     * );
     * Database.
     * Executar("Insert into usuarios (username, senha) values ('admin', 'admin')");
     * 
     * ResultSet a = Database.Ler("SELECT * FROM usuarios");
     * 
     * if (a.next()) {
     * 
     * String username = a.getString("username");
     * String senha = a.getString("senha");
     * System.out.println("Student #" + ": " + username + ", " + senha);
     * }
     * Database.Desconectar();
     */
}
