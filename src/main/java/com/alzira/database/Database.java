package com.alzira.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static String jdbcURL = "jdbc:h2:file:./src/main/resources/DATABASE/database;AUTO_SERVER=TRUE";
    // private static String jdbcURL = "jdbc:h2:file:X:\\Alunos\\081210040\\database;AUTO_SERVER=TRUE";
    private static String username = "SA";
    private static String password = "1234";

    private static Connection connection;

    public static Connection Conectar() throws InterruptedException, SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(jdbcURL, username, password);
        }

        return connection;
    }

    public static void Desconectar() throws SQLException {
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
}
