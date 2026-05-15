package com.fatesg.web_api.dtos;

public class MesAnoDto {
    private short mes;
    private short ano;

    public MesAnoDto() {
    }

    public MesAnoDto(short m, short a) {
        this.mes = m;
        this.ano = a;
    }

    public short getMes(){
        return mes;
    }

    public short getAno(){
        return ano;
    }
}
