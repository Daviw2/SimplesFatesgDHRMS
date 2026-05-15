package com.fatesg.mapper;

public interface MapperInterface<T, U> {
    T toModel(U dto);
    U toDto(T model);
}
