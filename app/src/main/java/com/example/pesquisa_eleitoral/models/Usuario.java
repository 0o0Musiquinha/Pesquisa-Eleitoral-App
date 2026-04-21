package com.example.pesquisa_eleitoral.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"usu_email"},
        unique = true)})
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "usu_id")
    private int id;

    @ColumnInfo(name = "usu_email")
    private String email;

    @ColumnInfo(name = "usu_senha")
    private String senha;

    @ColumnInfo(name = "usu_tipo")
    private int tipo;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
