package com.fatesg.mapper;

import com.fatesg.biblioteca.dtos.CargoDto;
import com.fatesg.model.Cargo;

public class CargoMapper implements MapperInterface<Cargo, CargoDto> {
    public Cargo toModel(CargoDto dto) {
        return new Cargo(
                0,
                dto.getDescricao(),
                null,
                null);
    }

    public CargoDto toDto(Cargo model) {
        return new CargoDto(model.getDescricao());
    }
}
