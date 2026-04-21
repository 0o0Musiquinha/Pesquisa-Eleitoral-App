package com.example.pesquisa_eleitoral.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pesquisa_eleitoral.models.Candidato;

import java.util.List;

@Dao
public interface CandidatoDAO {
    @Query("SELECT can_nome, can_partido, can_img FROM candidato")
    List<Candidato> getAll();

    @Query("SELECT * FROM candidato WHERE can_id IN (:candidatoIds)")
    List<Candidato> loadAllByIds(int[] candidatoIds);

    @Query("SELECT * FROM candidato WHERE can_nome = :nome")
    Candidato findByName(String nome);

    @Insert
    void insertAll(Candidato... candidatos);

    @Delete
    void delete(Candidato candidatos);
}
