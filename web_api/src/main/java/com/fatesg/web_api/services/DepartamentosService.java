package com.fatesg.web_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fatesg.biblioteca.dtos.DepartamentoDto;
import com.fatesg.web_api.apis.ServidorDeDadosDepartamentoApi;

@Service
public class DepartamentosService {
    ServidorDeDadosDepartamentoApi stub;

    public DepartamentosService() {
        this.stub = new ServidorDeDadosDepartamentoApi();
        this.stub.Conectar();
    }

    public List<DepartamentoDto> getDepartamentos(int limite, int offset) {
        try {
            var departamentos = this.stub.listarDepartamentos(limite, offset);
            return departamentos;
        } catch (Exception e) {
            System.err.println("Erro na comunicação com o servidor no getDepartamentos:");
            e.printStackTrace();
            throw new RuntimeException("Erro na comunicação com o servidor ao listar departamentos", e);
        }
    }

    public DepartamentoDto getDepartamentoById(String id) {
        try {
            var departamento = this.stub.obterDepartamentoPorId(id);
            return departamento;
        } catch (Exception e) {
            System.err.println("Erro na comunicação com o servidor no getDepartamentoById:");
            e.printStackTrace();
            throw new RuntimeException("Erro na comunicação com o servidor ao obter departamento", e);
        }
    }
}
