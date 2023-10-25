package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDAO {
    
        public static boolean validaLogin(String usuario, String senha) throws ClassNotFoundException{
        
        Connection conexao = ConexaoDAO.conectaBD();
        
        if (conexao != null) {
            try {                
                String sql = "SELECT * FROM dbo.Login WHERE Username = '" + usuario + "' AND Senha = '" + senha + "'";
                PreparedStatement statement = conexao.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                
                return resultSet.next();
                
            } catch (SQLException e) {
                System.err.println("Erro ao executar consulta: " + e.getMessage());                
            } finally {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
        
        return false;
    }    
}