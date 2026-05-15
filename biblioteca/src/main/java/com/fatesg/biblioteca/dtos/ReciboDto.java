package com.fatesg.biblioteca.dtos;

import java.io.Serializable;
import java.util.HashMap;

public class ReciboDto implements Serializable {
    private byte mesReferencia;
    private short anoReferencia;
    private int idFuncionario;
    private SalarioDto salarioBruto;
    private double salarioLiquido;
    private HashMap<String, Double> descontos;

    public ReciboDto() {
    }

    public ReciboDto(byte mesReferencia, short anoReferencia, int idFuncionario, SalarioDto salarioBruto,
            double salarioLiquido, HashMap<String, Double> descontos) {
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.idFuncionario = idFuncionario;
        this.salarioBruto = salarioBruto;
        this.salarioLiquido = salarioLiquido;
        this.descontos = descontos;
    }

    public ReciboDto(byte mesReferencia, short anoReferencia, int idFuncionario, SalarioDto salarioBruto) {
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.idFuncionario = idFuncionario;
        this.salarioBruto = salarioBruto;
        this.salarioLiquido = 0.0;
        this.descontos = new HashMap<String, Double>();
    }

    public byte getMesReferencia() {
        return mesReferencia;
    }

    public short getAnoReferencia() {
        return anoReferencia;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public SalarioDto getSalarioBruto() {
        return salarioBruto;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(double valor) {
        salarioLiquido = valor;
    }

    public HashMap<String, Double> getDescontos() {
        return descontos;
    }

    public void addDesconto(String descricao, double valor) {
        this.descontos.put(descricao, valor);
    }
}
