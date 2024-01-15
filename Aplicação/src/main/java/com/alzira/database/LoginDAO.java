
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
        
        return rs;
    }
}