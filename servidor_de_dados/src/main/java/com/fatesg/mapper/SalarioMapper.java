package com.fatesg.mapper;

import java.util.Date;

import com.fatesg.biblioteca.dtos.SalarioDto;
import com.fatesg.model.Salario;

public class SalarioMapper implements MapperInterface<Salario, SalarioDto> {
    public Salario toModel(SalarioDto dto) {
        return new Salario(
                dto.getIdFuncionario(),
                (int) dto.getValor(),
                Date.from(new Date().toInstant()),
                Date.from(new Date().toInstant()));
    }

    public SalarioDto toDto(Salario model) {
        return new SalarioDto(model.getFuncionarioId(), model.getValor());
    }
}
