package com.fatesg.services;

import java.util.List;

import com.fatesg.biblioteca.dtos.FuncionarioDto;
import com.fatesg.dao.FuncionarioDao;
import com.fatesg.database.MysqlConn;
import com.fatesg.log.Log;
import com.fatesg.mapper.FuncionarioMapper;

public class FuncionarioService {
    private FuncionarioDao funcionarioDao;

    public FuncionarioService() {
        var conn = MysqlConn.getConn();
        this.funcionarioDao = new FuncionarioDao(conn);
    }

    public FuncionarioDto obterFuncionarioPorId(int id) {
        FuncionarioDto funcionario = null;
        try {
            var func = funcionarioDao.Buscar(String.valueOf(id));
            if (func != null) {
                funcionario = new FuncionarioMapper().toDto(func);
            }
        } catch (Exception e) {
            Log.error("Erro ao obter funcionário por ID: " + e.getMessage());
        }
        return funcionario;
    }

    public List<FuncionarioDto> listarFuncionarios(int limite, int offset) {
        List<FuncionarioDto> funcionarios = null;
        try {
            var lista = funcionarioDao.Listar(limite, offset);
            funcionarios = lista
                    .stream()
                    .map(funcionario -> new FuncionarioMapper().toDto(funcionario))
                    .toList();
        } catch (Exception e) {
            Log.error("Erro ao listar funcionários: " + e.getMessage());
        }
        return funcionarios;
    }

    public int obterQtdeFuncionarios() {
        int qtde = 0;
        try {
            qtde = funcionarioDao.Contar();
        } catch (Exception e) {
            Log.error("Erro ao obter quantidade de funcionários: " + e.getMessage());
        }
        return qtde;
    }
}
