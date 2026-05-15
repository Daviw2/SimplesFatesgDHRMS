package com.fatesg.model;

import java.util.Date;

public class DepartamentoGerente extends DepartamentoFuncionario {
    
    public DepartamentoGerente(String departamentoId, int funcionarioId, Date desde, Date ate) {
        super(departamentoId, funcionarioId, desde, ate);
    }
}
