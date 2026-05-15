package com.fatesg.web_api.services;

import org.springframework.stereotype.Service;

import com.fatesg.biblioteca.dtos.FolhaDto;
import com.fatesg.biblioteca.dtos.ReciboDto;
import com.fatesg.biblioteca.interfaces.ClienteDeCalculoFolhaInterface;
import com.fatesg.web_api.apis.ServidorDeCalculoApi;

@Service
public class FolhaDePagamentoService implements ClienteDeCalculoFolhaInterface {
    private ServidorDeCalculoApi stub;

    public FolhaDePagamentoService() {
        this.stub = new ServidorDeCalculoApi();   
    }

    @Override
    public FolhaDto calcularFolhaDePagamento(byte mes, short ano) {
        return this.stub.calcularFolhaDePagamento(mes, ano);
    }

    @Override
    public FolhaDto calcularFolhaDePagamentoDoDepartamento(String arg0, byte arg1, short arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularFolhaDePagamentoDoDepartamento'");
    }

    @Override
    public ReciboDto calcularReciboDePagamento(int idFuncionario, byte mes, short ano) {
        return this.stub.calcularReciboDePagamento(idFuncionario, mes, ano);
    }

    @Override
    public double calcularSalarioLiquido(double salarioBruto) {
        return this.stub.calcularSalarioLiquido(salarioBruto);
    }
}
