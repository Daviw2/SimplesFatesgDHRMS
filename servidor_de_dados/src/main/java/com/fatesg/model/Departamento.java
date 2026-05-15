package com.fatesg.model;

public class Departamento {
    private String id;
    private String nome;

    public Departamento(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
