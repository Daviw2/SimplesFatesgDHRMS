package com.fatesg.biblioteca.dtos;

import java.io.Serializable;
import java.util.Date;

public class FuncionarioDto implements Serializable {
    private int id;
    private String nome;
    private String sobrenome;
    private char sexo;
    private Date dataNascimento;
    private Date dataContratacao;
    private SalarioDto salario;
    private CargoDto cargo;
    private DepartamentoDto departamento;

    public FuncionarioDto(int id, String nome, String sobrenome, char sexo, Date dataNascimento, Date dataContratacao) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.dataContratacao = dataContratacao;
    }

    public void setSalario(SalarioDto salario) {
        this.salario = salario;
    }

    public void setCargo(CargoDto cargo) {
        this.cargo = cargo;
    }

    public void setDepartamento(DepartamentoDto departamento) {
        this.departamento = departamento;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public char getSexo() {
        return sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public SalarioDto getSalario() {
        return salario;
    }

    public CargoDto getCargo() {
        return cargo;
    }

    public DepartamentoDto getDepartamento() {
        return departamento;
    }
}
