package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.model.Author;
import ru.home.itinfo.repository.AuthorRepository;

@Service
public class AuthorService extends CommonService<Author, Long> {
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        super(authorRepository);
    }
}
