package com.example.pesquisa_eleitoral.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public Connection getConexao(){
        try{
            Connection conn = DriverManager.getConnection(/*URL:*/"jdbc:mysql://10.0.2.2:3306/p1_eleitores", /*USER:*/"root", /*PASSWORD:*/"");
            System.out.println("Conexão realizada com sucesso!");
            return conn;
        }
        catch(SQLException e){
            System.out.println("Erro ao conectar no BD"+ e.getMessage());
            return null;
        }
    }
}
