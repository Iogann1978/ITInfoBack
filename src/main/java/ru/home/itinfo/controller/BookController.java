package ru.home.itinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.model.Book;
import ru.home.itinfo.service.BookService;


@RestController
@RequestMapping("/book")
public class BookController extends CommonController<Book, Long> {
    @Autowired
    public BookController(BookService bookService) {
        super(bookService);
    }
}
