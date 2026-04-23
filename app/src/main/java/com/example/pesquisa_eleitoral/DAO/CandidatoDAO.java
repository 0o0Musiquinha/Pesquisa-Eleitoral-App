package com.example.pesquisa_eleitoral.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pesquisa_eleitoral.models.Candidato;

import java.util.List;

@Dao
public interface CandidatoDAO {
    @Query("SELECT * FROM candidato")
    List<Candidato> getAll();

    @Query("SELECT * FROM candidato WHERE can_id IN (:candidatoIds)")
    List<Candidato> loadAllByIds(int[] candidatoIds);

    @Query("SELECT * FROM candidato WHERE can_nome = :nome")
    Candidato findByName(String nome);

    @Insert
    void insert(Candidato candidato);

    @Query("UPDATE candidato " +
            "SET can_qtdvotos = (can_qtdvotos + 1)" +
            "WHERE can_id = :id"
    )
    void updateVotos(int id);

    @Update
    void update(Candidato candidato);

    @Delete
    void deleteAll(Candidato... candidatos);
}
