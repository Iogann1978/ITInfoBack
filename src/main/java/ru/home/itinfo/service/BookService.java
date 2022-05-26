package ru.home.itinfo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.home.itinfo.dto.AuthorDTO;
import ru.home.itinfo.dto.BookDTO;
import ru.home.itinfo.dto.DescriptDTO;
import ru.home.itinfo.mapper.BookMapper;
import ru.home.itinfo.model.Author;
import ru.home.itinfo.model.Book;
import ru.home.itinfo.repository.impl.BookRepository;

@Service
@Slf4j
public class BookService extends CommonService<BookDTO, Book, Long> {
    private final AuthorService authorService;
    @Autowired
    public BookService(
            BookRepository bookRepository,
            AuthorService authorService,
            BookMapper bookMapper) {
        super(bookRepository, bookMapper, "Книга");
        this.authorService = authorService;
    }

    public void merge(BookDTO dto) {
        log.info("tags: {}", dto.getTags());
        if (!CollectionUtils.isEmpty(dto.getAuthors())) {
            for (AuthorDTO a : dto.getAuthors()) {
                Author na = authorService.getAuthor(a.getName().trim());
                a.setId(na.getId());
                a.setName(na.getName());
            }
        }
        if (!CollectionUtils.isEmpty(dto.getDescripts())) {
            for (DescriptDTO d : dto.getDescripts()) {
                d.setInfoId(dto.getId());
            }
        }
    }
}
