package com.fatesg.biblioteca.dtos;

import java.io.Serializable;

public class CargoDto implements Serializable{
    private String descricao;

    public CargoDto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
