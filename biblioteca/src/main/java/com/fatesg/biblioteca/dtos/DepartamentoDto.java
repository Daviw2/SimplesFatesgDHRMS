package com.fatesg.biblioteca.dtos;

import java.io.Serializable;

public class DepartamentoDto implements Serializable{
    private String id;
    private String nome;

    public DepartamentoDto(String id, String nome) {
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
