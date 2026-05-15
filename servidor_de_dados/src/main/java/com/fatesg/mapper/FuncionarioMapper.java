package com.fatesg.mapper;

import com.fatesg.biblioteca.dtos.CargoDto;
import com.fatesg.biblioteca.dtos.DepartamentoDto;
import com.fatesg.biblioteca.dtos.FuncionarioDto;
import com.fatesg.biblioteca.dtos.SalarioDto;
import com.fatesg.model.Cargo;
import com.fatesg.model.Departamento;
import com.fatesg.model.Funcionario;
import com.fatesg.model.Salario;

public class FuncionarioMapper implements MapperInterface<Funcionario, FuncionarioDto> {
    public Funcionario toModel(FuncionarioDto dto) {
        return new Funcionario(
                dto.getId(),
                dto.getNome(),
                dto.getSobrenome(),
                dto.getSexo(),
                dto.getDataNascimento(),
                dto.getDataContratacao());
    }

    public FuncionarioDto toDto(Funcionario model) {
        FuncionarioDto funcionarioDto = new FuncionarioDto(
                model.getId(),
                model.getNome(),
                model.getSobrenome(),
                model.getSexo(),
                model.getDataNascimento(),
                model.getDataContratacao());

        funcionarioDto.setSalario(null);
        funcionarioDto.setCargo(null);
        funcionarioDto.setDepartamento(null);

        return funcionarioDto;
    }

    public FuncionarioDto toDto(Funcionario model, Salario salario, Cargo cargo, Departamento departamento) {
        FuncionarioDto funcionarioDto = new FuncionarioDto(
                model.getId(),
                model.getNome(),
                model.getSobrenome(),
                model.getSexo(),
                model.getDataNascimento(),
                model.getDataContratacao());

        SalarioDto salarioDto = new SalarioMapper().toDto(salario);
        funcionarioDto.setSalario(salarioDto);

        CargoDto cargoDto = new CargoMapper().toDto(cargo);
        funcionarioDto.setCargo(cargoDto);

        DepartamentoDto departamentoDto = new DepartamentoMapper().toDto(departamento);
        funcionarioDto.setDepartamento(departamentoDto);

        return funcionarioDto;
    }
}
