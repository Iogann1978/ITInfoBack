package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.home.itinfo.dto.AuthorDTO;
import ru.home.itinfo.model.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends CommonMapper<AuthorDTO, Author> {
    @Mappings({
            @Mapping(target = "count", expression = "java(entity.getBooks() != null ? entity.getBooks().size() : 0)")
    })
    AuthorDTO entityToDto(Author entity);
}
