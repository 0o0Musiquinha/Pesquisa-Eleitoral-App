package com.example.pesquisa_eleitoral.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pesquisa_eleitoral.models.Problema;

import java.util.List;

@Dao
public interface ProblemaDAO {

    @Query("SELECT * FROM problema")
    List<Problema> getAll();

    @Query("SELECT * FROM problema WHERE pro_id IN (:id)")
    List<Problema> loadAllByIds(int[] id);

    @Query("SELECT * FROM problema WHERE pro_nome = :nome")
    Problema findByName(String nome);

    @Insert
    void insert(Problema problemas);
    @Query("UPDATE problema " +
            "SET pro_qtdvoto = (pro_qtdvoto + 1)" +
            "WHERE pro_nome = :nome"
    )
    void updateVotos(String nome);


    @Insert
    void insertAll(Problema... problemas);

    @Delete
    void delete(Problema problemas);
}
