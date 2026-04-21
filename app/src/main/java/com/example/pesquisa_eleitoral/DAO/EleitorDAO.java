package com.example.pesquisa_eleitoral.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pesquisa_eleitoral.models.Eleitor;

import java.util.List;

@Dao
public interface EleitorDAO {

    @Query("SELECT * FROM eleitor")
    List<Eleitor> getAll();

    @Query("SELECT * FROM eleitor WHERE ele_id IN (:id)")
    List<Eleitor> loadAllByIds(int[] id);

    @Query("SELECT * FROM eleitor WHERE ele_nome = :nome")
    Eleitor findByName(String nome);

    @Insert
    void insertAll(Eleitor... eleitores);

    @Delete
    void delete(Eleitor eleitores);
}
