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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService extends CommonService<BookDTO, Book, Long> {
    private final AuthorService authorService;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(
            BookRepository bookRepository,
            AuthorService authorService,
            BookMapper bookMapper) {
        super(bookRepository, bookMapper, "Книга");
        this.authorService = authorService;
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public void merge(BookDTO dto) {
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

    public List<BookDTO> findByIsbn(String isbn) {
        return bookRepository.findAllByIsbn(isbn)
                .stream().map(bookMapper::entityToDto).collect(Collectors.toList());
    }
}
