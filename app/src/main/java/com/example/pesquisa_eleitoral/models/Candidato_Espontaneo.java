package com.example.pesquisa_eleitoral.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Candidato_Espontaneo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "esp_id")
    private int id;

    @ColumnInfo(name = "esp_nome")
    private String nome;

    @ColumnInfo(name = "esp_qtdvotos")
    private int qtdVotos;

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

    public int getQtdVotos() {
        return qtdVotos;
    }

    public void setQtdVotos(int qtdVotos) {
        this.qtdVotos = qtdVotos;
    }
}
