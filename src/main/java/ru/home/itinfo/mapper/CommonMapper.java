package ru.home.itinfo.mapper;

public interface CommonMapper<T1, T2> {
    T2 dtoToEntity(T1 dto);
    T1 entityToDto(T2 entity);
}
