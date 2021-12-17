package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.model.Book;
import ru.home.itinfo.repository.BookRepository;

@Service
public class BookService extends CommonService<Book, Long> {
    @Autowired
    public BookService(BookRepository bookRepository) {
        super(bookRepository);
    }
}
