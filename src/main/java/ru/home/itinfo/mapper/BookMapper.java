package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import ru.home.itinfo.dto.BookDTO;
import ru.home.itinfo.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper extends CommonMapper<BookDTO, Book> {
}
