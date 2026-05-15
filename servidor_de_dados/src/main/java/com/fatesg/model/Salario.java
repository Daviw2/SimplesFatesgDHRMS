package com.fatesg.model;

import java.util.Date;

public class Salario {
    private int funcionarioId;
    private int valor;
    private Date desde;
    private Date ate;

    public Salario(int funcionarioId, int valor, Date desde, Date ate) {
        this.funcionarioId = funcionarioId;
        this.valor = valor;
        this.desde = desde;
        this.ate = ate;
    }

    public boolean isAtivo() {
        Date hoje = new Date();
        return (desde.before(hoje) || desde.equals(hoje)) && (ate.after(hoje) || ate.equals(hoje));
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public int getValor() {
        return valor;
    }

    public Date getDesde() {
        return desde;
    }

    public Date getAte() {
        return ate;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public void setAte(Date ate) {
        this.ate = ate;
    }
}
