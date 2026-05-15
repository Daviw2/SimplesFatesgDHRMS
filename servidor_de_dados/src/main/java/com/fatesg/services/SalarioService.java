package com.fatesg.services;

import java.util.List;

import com.fatesg.biblioteca.dtos.SalarioDto;
import com.fatesg.dao.SalarioDao;
import com.fatesg.database.MysqlConn;
import com.fatesg.log.Log;
import com.fatesg.mapper.SalarioMapper;

public class SalarioService {
    private SalarioDao salarioDao;

    public SalarioService() {
        var conn = MysqlConn.getConn();
        this.salarioDao = new SalarioDao(conn);
    }

    public List<SalarioDto> listarSalarios(int limite, int offset) {
        List<SalarioDto> salarios = null;
        try {
            var lista = salarioDao.Listar(limite, offset);
            salarios = lista
                    .stream()
                    .map(salario -> new SalarioMapper().toDto(salario))
                    .toList();
        } catch (Exception e) {
            Log.error("Erro ao listar salários: " + e.getMessage());
        }
        return salarios;
    }

    public SalarioDto obterSalarioPorId(int id){
        SalarioDto salario = null;
        try {
            var sal = salarioDao.Buscar(String.valueOf(id));
            if(sal != null){
                salario = new SalarioMapper().toDto(sal);
            }
        } catch (Exception e) {
            Log.error("Erro ao obter o salario por ID:"+e.getMessage());
        }
        return salario;
    }

    public int obterQtdeSalarios() {
        int qtde = 0;
        try {
            qtde = salarioDao.Contar();
        } catch (Exception e) {
            Log.error("Erro ao obter quantidade de salários: " + e.getMessage());
        }
        return qtde;
    }
}
