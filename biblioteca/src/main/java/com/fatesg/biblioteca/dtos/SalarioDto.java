package com.fatesg.biblioteca.dtos;

import java.io.Serializable;

public class SalarioDto implements Serializable{
    private int idFuncionario;
    private double valor;

    public SalarioDto(int idFuncionario, double valor) {
        this.idFuncionario = idFuncionario;
        this.valor = valor;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public double getValor() {
        return valor;
    }
}
