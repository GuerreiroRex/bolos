
package com.alzira.database;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LoginDAO {
    
    Connection conn;
    
    public ResultSet verificaLogin(String usuarioInput, String senhaInput) throws Exception{
        conn = new Database().Conectar();
        
        String sql = "SELECT * FROM login where username = ? and senha = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, usuarioInput);
        pstm.setString(2, senhaInput);
        
        ResultSet rs = pstm.executeQuery();
        
//        String db_username = "null";
//        String db_senha = "null";
//        
//        while (resultado.next()) {
//            db_username = resultado.getString("username");
//            db_senha = resultado.getString("senha");
//
//            if ((db_username.equals(usuarioInput) & db_senha.equals(senhaInput))) {
//                return true;
//            }
//        }
//        return false;
        return rs;
    }
}
