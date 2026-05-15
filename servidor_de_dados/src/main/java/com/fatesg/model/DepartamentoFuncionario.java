package com.fatesg.model;

import java.util.Date;

public class DepartamentoFuncionario {
    protected String departamentoId;
    protected int funcionarioId;
    protected Date desde;
    protected Date ate;

    public DepartamentoFuncionario(String departamentoId, int funcionarioId, Date desde, Date ate) {
        this.departamentoId = departamentoId;
        this.funcionarioId = funcionarioId;
        this.desde = desde;
        this.ate = ate;
    }

    public boolean isAtivo() {
        Date hoje = new Date();
        return (desde.before(hoje) || desde.equals(hoje)) && (ate.after(hoje) || ate.equals(hoje));
    }

    public String getDepartamentoId() {
        return departamentoId;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public Date getDesde() {
        return desde;
    }

    public Date getAte() {
        return ate;
    }
}
