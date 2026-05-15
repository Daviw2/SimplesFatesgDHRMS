package com.fatesg.model;

import java.util.Date;

public class Funcionario {
    private int id;
    private String nome;
    private String sobrenome;
    private char sexo;
    private Date dataNascimento;
    private Date dataContratacao;

    public Funcionario(int id, String nome, String sobrenome, char sexo, Date dataNascimento, Date dataContratacao) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.dataContratacao = dataContratacao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNomeCompleto() {
        return nome + " " + sobrenome;
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
}
