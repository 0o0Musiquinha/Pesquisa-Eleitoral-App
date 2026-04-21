package com.example.pesquisa_eleitoral.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.pesquisa_eleitoral.models.Candidato_Espontaneo;

import java.util.List;

@Dao
public interface Candidato_EspontaneoDAO {

    @Query("SELECT * FROM candidato_espontaneo")
    List<Candidato_Espontaneo> getAll();

    @Query("SELECT * FROM candidato_espontaneo WHERE esp_id IN (:id)")
    List<Candidato_Espontaneo> loadAllByIds(int[] id);

    @Query("SELECT * FROM candidato_espontaneo WHERE esp_nome = :nome")
    Candidato_Espontaneo findByName(String nome);

    @Insert
    void insertAll(Candidato_Espontaneo... candidatos_espontaneos);

    @Delete
    void delete(Candidato_Espontaneo candidatos_espontaneos);

}
