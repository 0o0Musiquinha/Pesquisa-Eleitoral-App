package com.example.pesquisa_eleitoral.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pesquisa_eleitoral.models.Usuario;

import java.util.List;

@Dao
public interface UsuarioDAO {

    @Query("SELECT * FROM Usuario")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuario WHERE usu_id IN (:usuarioIds)")
    List<Usuario> loadAllByIds(int[] usuarioIds);

    @Query("SELECT * FROM usuario " +
            "WHERE usu_email = :email AND usu_senha = :senha")
    Usuario findByEmail(String email, String senha);

    @Insert
    void insertAll(Usuario... usuarios);

    @Delete
    void delete(Usuario usuarios);

}
