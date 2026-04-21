package com.example.pesquisa_eleitoral.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Eleitor {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ele_id")
    private int id;

    @ColumnInfo(name = "ele_nome")
    private String nome;

    @ColumnInfo(name = "ele_celular")
    private String celular;

    @ColumnInfo(name = "ele_datahora")
    private Long dataHora;

    @ColumnInfo(name = "ele_latitude")
    private double latitude;

    @ColumnInfo(name = "ele_longitude")
    private double longitude;

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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Long getDataHora() {
        return dataHora;
    }

    public void setDataHora(Long dataHora) {
        this.dataHora = dataHora;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
