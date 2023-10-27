package com.alzira.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static String jdbcURL = "jdbc:h2:file:./src/main/resources/DATABASE/database";
    //src\main\resources\DATABASE
    private static String username = "SA";
    private static String password = "1234";

    public static ResultSet Executar(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcURL, username, password);

        //String sql = "SELECT * FROM students";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        connection.close();

        return resultSet;
    }

    /*
        Database.Executar("Create table usuarios (username varchar(256) primary key, senha varchar(256))");
        Database.Executar("Insert into usuarios (username, senha) values ('admin', 'admin')");

        ResultSet a = Database.Executar("SELECT * FROM usuarios");

        while (a.next()) {

            String username = a.getInt("username");
            String senha = a.getString("senha");
            System.out.println("Student #" + ": " + username + ", " + senha);
        }
     */
}
