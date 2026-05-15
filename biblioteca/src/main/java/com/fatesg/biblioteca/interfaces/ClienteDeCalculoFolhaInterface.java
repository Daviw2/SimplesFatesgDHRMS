package com.fatesg.biblioteca.interfaces;

import java.util.HashMap;
import com.fatesg.biblioteca.dtos.FolhaDto;
import com.fatesg.biblioteca.dtos.ReciboDto;

public interface ClienteDeCalculoFolhaInterface {
    public FolhaDto calcularFolhaDePagamento(byte mes, short ano);
    public FolhaDto calcularFolhaDePagamentoDoDepartamento(String idDepartamento, byte mes, short ano);
    public ReciboDto calcularReciboDePagamento(int idFuncionario, byte mesReferencia, short anoReferencia);
    public double calcularSalarioLiquido(double salarioBruto);
}
