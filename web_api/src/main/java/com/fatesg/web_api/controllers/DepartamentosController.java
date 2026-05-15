package com.fatesg.web_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatesg.biblioteca.dtos.DepartamentoDto;
import com.fatesg.web_api.services.DepartamentosService;

@RestController
@RequestMapping("/api/v1/departamentos")
public class DepartamentosController {
    @Autowired
    DepartamentosService servico;

    @GetMapping()
    public ResponseEntity<List<DepartamentoDto>> getDepartamentos(
            @RequestParam(defaultValue = "15") int limite,
            @RequestParam(defaultValue = "0") int offset) {
        try {
            var departamentos = servico.getDepartamentos(limite, offset);
            if (departamentos == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(departamentos);
        } catch (Exception e) {
            System.err.println("Erro no getDepartamentos:");
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDto> getDepartamentoById(@PathVariable String id) {
        try {
            var departamento = servico.getDepartamentoById(id);
            if (departamento == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(departamento);
        } catch (Exception e) {
            System.err.println("Erro no getDepartamentoById:");
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
