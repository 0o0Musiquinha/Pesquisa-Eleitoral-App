package com.example.pesquisa_eleitoral.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Candidato {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "can_id")
    private int id;

    @ColumnInfo(name = "can_nome")
    private String nome;

    @ColumnInfo(name = "can_partido")
    private String partido;

    @ColumnInfo(name = "can_img")
    private String img;

    @ColumnInfo(name = "can_qtdvotos")
    private int qtdVotos;

    @ColumnInfo(name = "can_tipo")
    private int tipo;

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

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getQtdVotos() {
        return qtdVotos;
    }

    public void setQtdVotos(int qtdVotos) {
        this.qtdVotos = qtdVotos;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
