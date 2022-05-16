package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.BookDTO;
import ru.home.itinfo.mapper.BookMapper;
import ru.home.itinfo.model.Book;
import ru.home.itinfo.repository.impl.BookRepository;

@Service
public class BookService extends CommonService<BookDTO, Book, Long> {
    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        super(bookRepository, bookMapper, "Книга");
    }
}
