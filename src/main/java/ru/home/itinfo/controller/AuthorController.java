package ru.home.itinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.model.Author;
import ru.home.itinfo.service.AuthorService;


@RestController
@RequestMapping("/author")
public class AuthorController extends CommonController<Author, Long> {
    @Autowired
    public AuthorController(AuthorService authorService) {
        super(authorService);
    }
}
