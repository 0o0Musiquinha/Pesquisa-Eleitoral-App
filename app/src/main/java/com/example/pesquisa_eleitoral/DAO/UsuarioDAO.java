package com.example.pesquisa_eleitoral.DAO;

import com.example.pesquisa_eleitoral.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Conexao conexao;
    private Connection conn;

    public UsuarioDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public List<Usuario> getUsuarios(String email, String senha){
        String sql = "SELECT usu_id, usu_email, usu_senha, usu_tipo "
                + "FROM usuario "
                + "WHERE usu_email = " + email + " AND usu_senha = " + senha + ";";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            List<Usuario> listaUsuarios = new ArrayList(); //Preparo uma lista de objetos que vou armazenar a consulta

            //Percorre rs e salva as informações dentro de um objeto Produto e depois adiciona na lista
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("can_id"));
                usuario.setEmail(rs.getString("usu_email"));
                usuario.setSenha(rs.getString("usu_senha"));
                usuario.setTipo(rs.getInt("usu_tipo"));

                listaUsuarios.add(usuario);
            }
            return listaUsuarios;

        }catch (SQLException ex){
            System.out.println("Erro ao consultar todos os candidatos: "+ ex.getMessage());
            return null;
        }
    }
}
