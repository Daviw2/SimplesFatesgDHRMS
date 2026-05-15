package com.fatesg.services;

import java.util.List;

import com.fatesg.biblioteca.dtos.DepartamentoDto;
import com.fatesg.dao.DepartamentoDao;
import com.fatesg.database.MysqlConn;
import com.fatesg.log.Log;
import com.fatesg.mapper.DepartamentoMapper;

public class DepartamentoService {
    private DepartamentoDao departamentoDao;

    public DepartamentoService() {
        var conn = MysqlConn.getConn();
        this.departamentoDao = new DepartamentoDao(conn);
    }

    public List<DepartamentoDto> listarDepartamentos(int limite, int offset) {
        List<DepartamentoDto> departamentos = null;
        try {
            var lista = departamentoDao.Listar(limite, offset);
            departamentos = lista
                    .stream()
                    .map(departamento -> new DepartamentoMapper().toDto(departamento))
                    .toList();
        } catch (Exception e) {
            Log.error("Erro ao listar departamentos: " + e.getMessage());
        }
        return departamentos;
    }

    public DepartamentoDto obterDepartamentoPorId(String id) {
        DepartamentoDto departamento = null;
        try {
            var depto = departamentoDao.Buscar(id);
            if (depto != null) {
                departamento = new DepartamentoMapper().toDto(depto);
            }
        } catch (Exception e) {
            Log.error("Erro ao obter departamento por ID: " + e.getMessage());
        }
        return departamento;
    }

    public int obterQtdeDepartamentos() {
        int qtde = 0;
        try {
            qtde = departamentoDao.Contar();
        } catch (Exception e) {
            Log.error("Erro ao obter quantidade de departamentos: " + e.getMessage());
        }
        return qtde;
    }
}
