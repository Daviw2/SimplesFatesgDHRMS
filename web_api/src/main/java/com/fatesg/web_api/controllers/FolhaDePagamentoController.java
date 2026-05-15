package com.fatesg.web_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatesg.biblioteca.dtos.FolhaDto;
import com.fatesg.biblioteca.dtos.ReciboDto;
import com.fatesg.web_api.dtos.MesAnoDto;
import com.fatesg.web_api.services.FolhaDePagamentoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/folha-de-pagamento")
public class FolhaDePagamentoController {
    @Autowired
    FolhaDePagamentoService servico;

    @GetMapping("calcular-salario/{salarioBruto}")
    public ResponseEntity<Double> getCalcularSalario(@PathVariable() double salarioBruto) {
        try {
            Double retorno = servico.calcularSalarioLiquido(salarioBruto);
            return ResponseEntity.ok(retorno);
        } catch (Exception e) {
            System.err.println("Erro no getCalcularSalario:");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("calcular-recibo/{idFuncionario}")
    public ResponseEntity<ReciboDto> getCalcularRecibo(
            @PathVariable() int idFuncionario,
            @RequestParam(defaultValue = "1") short mes,
            @RequestParam(defaultValue = "2026") short ano) {
        try {
            ReciboDto retorno = servico.calcularReciboDePagamento(idFuncionario, (byte) mes, ano);
            return ResponseEntity.ok(retorno);
        } catch (Exception e) {
            System.err.println("Erro no getCalcularRecibo:");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("calcular-folha")
    public ResponseEntity<FolhaDto> postCalcularFolha(@RequestBody MesAnoDto parametros){
        try {
            FolhaDto retorno = servico.calcularFolhaDePagamento((byte) parametros.getMes(), parametros.getAno());
            return ResponseEntity.ok(retorno);
        } catch (Exception e) {
            System.err.println("Erro no getCalcularRecibo:");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
