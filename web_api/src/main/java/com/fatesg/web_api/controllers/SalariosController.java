package com.fatesg.web_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatesg.biblioteca.dtos.SalarioDto;
import com.fatesg.web_api.services.SalarioService;

@RestController
@RequestMapping("api/v1/salarios")
public class SalariosController {
    @Autowired
    SalarioService service;

    @GetMapping("")
    public ResponseEntity<List<SalarioDto>> getSalarios(
            @RequestParam(defaultValue = "15") int limite,
            @RequestParam(defaultValue = "0") int offset) {
        try {
            var salarios = service.obterSalarios(limite, offset);

            if (salarios == null)
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok(salarios);
        } catch (Exception e) {
            System.err.println("Erro no getSalarios:");
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{idFuncionario}")
    public ResponseEntity<SalarioDto> getSalarioPorIdFuncionario(@PathVariable() int idFuncionario) {
        try {
            var salario = service.obterSalarioPorIdFuncionario(idFuncionario);

            if (salario == null)
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok(salario);

        } catch (Exception e) {
            System.err.println("Erro no getSalarioPorIdFuncionario:");
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
