package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import ru.home.itinfo.dto.AuthorDTO;
import ru.home.itinfo.model.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends CommonMapper<AuthorDTO, Author> {
}
