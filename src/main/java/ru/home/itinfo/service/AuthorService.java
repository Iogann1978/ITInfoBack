package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.AuthorDTO;
import ru.home.itinfo.dto.BookDTO;
import ru.home.itinfo.mapper.AuthorMapper;
import ru.home.itinfo.mapper.BookMapper;
import ru.home.itinfo.model.Author;
import ru.home.itinfo.repository.impl.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService extends CommonService<AuthorDTO, Author, Long> {
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Autowired
    public AuthorService(
            AuthorRepository authorRepository,
            AuthorMapper authorMapper,
            BookMapper bookMapper) {
        super(authorRepository, authorMapper, "Автор");
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    public Author getAuthor(String name) {
        return authorRepository.findFirstByName(name)
                .orElseGet(() -> saveEntity(Author.builder().name(name.trim()).build()));
    }

    public List<BookDTO> findByAuthor(String author) {
        return authorRepository.getAllByNameLike(author).stream()
                .flatMap(a -> a.getBooks().stream()).map(bookMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
