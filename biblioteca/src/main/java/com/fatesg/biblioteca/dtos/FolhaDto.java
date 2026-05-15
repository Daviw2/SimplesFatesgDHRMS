package com.fatesg.biblioteca.dtos;

import java.io.Serializable;
import java.util.ArrayList;

public class FolhaDto implements Serializable{
    private byte mes;
    private short ano;
    private ArrayList<ReciboDto> recibos;

    public FolhaDto() {
        super();
    }

    public FolhaDto(byte mes, short ano, ArrayList<ReciboDto> recibos) {
        this.mes = mes;
        this.ano = ano;
        this.recibos = recibos;
    }

    public FolhaDto(byte mes, short ano) {
        this.mes = mes;
        this.ano = ano;
        this.recibos = new ArrayList<ReciboDto>();
    }

    public void addRecibo(ReciboDto recibo) {
        this.recibos.add(recibo);
    }

    public byte getMes() {
        return mes;
    }

    public short getAno() {
        return ano;
    }

    public ArrayList<ReciboDto> getRecibos() {
        return recibos;
    }
}
