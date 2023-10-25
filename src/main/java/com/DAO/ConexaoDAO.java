package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
    
    public static Connection conectaBD() throws ClassNotFoundException{
        Connection conexao = null;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://CE-LAB6713;encrypt=false;database=teste;user=sa;password=123456";
            conexao = DriverManager.getConnection(url);
            System.out.println("Conexão bem-sucedida.");
            
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return conexao;
    }
}