package com.fatesg.web_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatesg.biblioteca.dtos.FuncionarioDto;
import com.fatesg.web_api.services.FuncionariosService;

@RestController
@RequestMapping("/api/v1/funcionarios")
public class FuncionariosController {
    @Autowired
    FuncionariosService servico;

    @GetMapping()
    public ResponseEntity<List<FuncionarioDto>> getFuncionarios(
            @RequestParam(defaultValue = "15") int limite,
            @RequestParam(defaultValue = "0") int offset) {
        try {
            var funcionarios = servico.getFuncionarios(limite, offset);
            if (funcionarios == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(funcionarios);
        } catch (Exception e) {
            System.err.println("Erro no getFuncionarios:");
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDto> getFuncionarioById(@PathVariable int id) {
        try {
            var funcionario = servico.getFuncionarioById(id);
            
            if (funcionario == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(funcionario);
        } catch (Exception e) {
            System.err.println("Erro no getFuncionarioById:");
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
