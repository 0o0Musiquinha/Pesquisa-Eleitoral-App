package com.example.pesquisa_eleitoral.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.pesquisa_eleitoral.models.Candidato_Espontaneo;
import com.example.pesquisa_eleitoral.models.Eleitor;

import java.util.List;

@Dao
public interface Candidato_EspontaneoDAO {

    @Query("SELECT * FROM candidato_espontaneo")
    List<Candidato_Espontaneo> getAll();

    @Query("SELECT * FROM candidato_espontaneo WHERE esp_id IN (:id)")
    List<Candidato_Espontaneo> loadAllByIds(int[] id);

    @Query("SELECT * FROM candidato_espontaneo WHERE esp_nome = :nome")
    Candidato_Espontaneo findByName(String nome);

    @Query("UPDATE candidato_espontaneo " +
            "SET esp_qtdvotos = (esp_qtdvotos + 1)" +
            "WHERE esp_id = :id"
    )
    void updateVotos(int id);

    @Insert
    void insert(Candidato_Espontaneo candidato_espontaneo);

    @Delete
    void delete(Candidato_Espontaneo candidatos_espontaneos);

}
