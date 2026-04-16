package com.example.pesquisa_eleitoral.DAO;

import com.example.pesquisa_eleitoral.models.Candidato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidatoDAO {

    private Conexao conexao;
    private Connection conn;

    public CandidatoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public List<Candidato> getCandidatos(){
        String sql = "SELECT can_id, can_nome, can_partido, can_imagem, can_qtdVotos, can_tipo "
                + "FROM candidato;";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            List<Candidato> listaCandidatos = new ArrayList(); //Preparo uma lista de objetos que vou armazenar a consulta

            //Percorre rs e salva as informações dentro de um objeto Produto e depois adiciona na lista
            while(rs.next()){
                Candidato candidato = new Candidato();
                candidato.setId(rs.getInt("can_id"));
                candidato.setNome(rs.getString("can_nome"));
                if(rs.getString("can_partido") == null){
                    candidato.setPartido("");
                } else{
                    candidato.setPartido(rs.getString("can_partido"));
                }
                candidato.setImg(rs.getString("can_imagem"));
                candidato.setQtdVotos(rs.getInt("can_qtdVotos"));
                candidato.setTipo(rs.getInt("can_tipo"));

                listaCandidatos.add(candidato);
            }
            return listaCandidatos;

        }catch (SQLException ex){
            System.out.println("Erro ao consultar todos os candidatos: "+ ex.getMessage());
            return null;
        }
    }
}
