package com.example.pesquisa_eleitoral.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"pro_nome"}, unique = true)})
public class Problema {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pro_id")
    private int id;

    @ColumnInfo(name = "pro_nome")
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
