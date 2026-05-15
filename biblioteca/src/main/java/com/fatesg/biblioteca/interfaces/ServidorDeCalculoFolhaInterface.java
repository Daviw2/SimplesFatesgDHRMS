package com.fatesg.biblioteca.interfaces;

import java.util.HashMap;
import com.fatesg.biblioteca.dtos.FolhaDto;
import com.fatesg.biblioteca.dtos.ReciboDto;

public interface ServidorDeCalculoFolhaInterface {
    public FolhaDto calcularFolhaDePagamento(byte mes, short ano, HashMap<String, Double> descontos);
    public FolhaDto calcularFolhaDePagamentoDoDepartamento(String idDepartamento, byte mes, short ano, HashMap<String, Double> descontos);
    public ReciboDto calcularReciboDePagamento(int idFuncionario, byte mesReferencia, short anoReferencia, HashMap<String, Double> descontos);
    public double calcularSalarioLiquido(double salarioBruto, HashMap<String, Double> descontos);
}
