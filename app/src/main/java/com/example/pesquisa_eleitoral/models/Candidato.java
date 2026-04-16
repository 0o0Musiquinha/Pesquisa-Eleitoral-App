package com.example.pesquisa_eleitoral.models;

public class Candidato {
    private int id;
    private String nome;
    private String partido;
    private String img;
    private int qtdVotos;
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
