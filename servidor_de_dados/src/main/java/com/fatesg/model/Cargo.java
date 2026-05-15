package com.fatesg.model;

import java.util.Date;

public class Cargo {
    private int funcionarioId;
    private String descricao;
    private Date desde;
    private Date ate;

    public Cargo(int funcionarioId, String descricao, Date desde, Date ate) {
        this.funcionarioId = funcionarioId;
        this.descricao = descricao;
        this.desde = desde;
        this.ate = ate;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDesde() {
        return desde;
    }

    public Date getAte() {
        return ate;
    }
}
