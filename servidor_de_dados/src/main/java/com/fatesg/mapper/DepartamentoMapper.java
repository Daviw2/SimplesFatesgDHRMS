package com.fatesg.mapper;

import com.fatesg.biblioteca.dtos.DepartamentoDto;
import com.fatesg.model.Departamento;

public class DepartamentoMapper implements MapperInterface<Departamento, DepartamentoDto> {
    public Departamento toModel(DepartamentoDto dto) {
        return new Departamento(
                dto.getId(),
                dto.getNome());
    }

    public DepartamentoDto toDto(Departamento model) {
        return new DepartamentoDto(
                model.getId(),
                model.getNome());
    }
}
