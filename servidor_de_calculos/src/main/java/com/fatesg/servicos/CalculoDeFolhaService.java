package com.fatesg.servicos;

import java.util.HashMap;
import java.util.List;

import com.fatesg.apis.ServidorDeDadosSalarioApi;
import com.fatesg.biblioteca.dtos.FolhaDto;
import com.fatesg.biblioteca.dtos.ReciboDto;
import com.fatesg.biblioteca.dtos.SalarioDto;
import com.fatesg.biblioteca.interfaces.ServidorDeCalculoFolhaInterface;

public class CalculoDeFolhaService implements ServidorDeCalculoFolhaInterface {
    private ServidorDeDadosSalarioApi stub;

    public CalculoDeFolhaService() {
        this.stub = new ServidorDeDadosSalarioApi();
        this.stub.Conectar();
    }

    @Override
    public FolhaDto calcularFolhaDePagamento(byte mes, short ano, HashMap<String, Double> descontos) {
        try {
            FolhaDto folha = new FolhaDto(mes, ano);
            int offset = 0;
            int limit = 50;
            List<SalarioDto> salarios;
            
            salarios = stub.listarSalarios(limit, offset);
            for (SalarioDto salarioDto : salarios) {
                // CORREÇÃO AQUI: Passamos o objeto salarioDto direto para não buscar no banco de novo
                ReciboDto recibo = processarRecibo(salarioDto, mes, ano, descontos);
                folha.addRecibo(recibo);
            }
            return folha;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método novo para evitar latência (Atividade 4)
    private ReciboDto processarRecibo(SalarioDto salarioBrutoAnual, byte mes, short ano, HashMap<String, Double> descontos) {
        double salarioBruto = salarioBrutoAnual.getValor() / 12;
        var recibo = new ReciboDto(mes, ano, salarioBrutoAnual.getIdFuncionario(), new SalarioDto(salarioBrutoAnual.getIdFuncionario(), salarioBruto));

        descontos.forEach((k, v) -> recibo.addDesconto(k, v));
        recibo.setSalarioLiquido(calcularSalarioLiquido(salarioBruto, descontos));
        return recibo;
    }

    @Override
    public ReciboDto calcularReciboDePagamento(int idFuncionario, byte mesReferencia, short anoReferencia, HashMap<String, Double> descontos) {
        try {
            SalarioDto salarioBrutoAnual = stub.obterSalarioPorId(idFuncionario);
            if (salarioBrutoAnual == null) throw new Exception("Salario nao encontrado");
            return processarRecibo(salarioBrutoAnual, mesReferencia, anoReferencia, descontos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public double calcularSalarioLiquido(double salarioBruto, HashMap<String, Double> descontos) {
        var salarioLiquido = salarioBruto;
        for (var desconto : descontos.values()) {
            salarioLiquido -= (salarioBruto * (desconto / 100d));
        }
        return salarioLiquido;
    }

    @Override
    public FolhaDto calcularFolhaDePagamentoDoDepartamento(String arg0, byte arg1, short arg2, HashMap<String, Double> arg3) {
        throw new UnsupportedOperationException("Unimplemented method");
    }
}