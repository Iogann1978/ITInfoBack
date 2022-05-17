package ru.home.itinfo.db;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.home.itinfo.config.JpaConfig;
import ru.home.itinfo.dto.BookDTO;
import ru.home.itinfo.model.*;
import ru.home.itinfo.service.*;

import java.nio.charset.StandardCharsets;
import java.util.Set;

@DataJpaTest
@Import({JpaConfig.class, AuthorService.class, PublisherService.class, TagService.class, FileService.class, DescriptService.class, BookService.class})
@ComponentScan(basePackages = "ru.home.itinfo.dto,ru.home.itinfo.mapper,ru.home.itinfo.model,ru.home.itinfo.repository")
@Transactional
@Slf4j
@ActiveProfiles("test")
public class ItInfoDbTests {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private TagService tagService;
    @Autowired
    private FileService fileService;
    @Autowired
    private DescriptService descriptService;
    @Autowired
    private BookService bookService;

    @Test
    void bookSaveTest() {
        Book book = Book.builder()
                .isbn("1234567890")
                .title("Test book")
                .pages(3)
                .rate(Rate.GOOD)
                .state(State.PLANNED)
                .year(2007)
                .build();
        Book savedBook = bookService.saveEntity(book);
        Assertions.assertNotNull(savedBook, "savedBook is null");

        Publisher publisher = Publisher.builder()
                .name("test publisher")
                .infos(Set.of(book))
                .build();
        Publisher savedPublisher = publisherService.saveEntity(publisher);

        Author author = Author.builder()
                .name("test author")
                .books(Set.of(savedBook))
                .build();
        Author savedAuthor = authorService.saveEntity(author);

        InfoFile file = InfoFile.builder()
                .filename("test_file.txt")
                .size(321L)
                .info(savedBook)
                .build();
        InfoFile savedFile = fileService.saveEntity(file);

        Descript descript = Descript.builder()
                .name("test descript")
                .text("Test".getBytes(StandardCharsets.UTF_8))
                .info(savedBook)
                .build();
        Descript savedDescript = descriptService.saveEntity(descript);

        Tag tag = Tag.builder()
                .tag("Java")
                .infos(Set.of(savedBook))
                .build();
        Tag savedTag = tagService.saveEntity(tag);

        log.info("book: {}", savedBook);
        log.info("author: {}", savedAuthor);
        log.info("publisher: {}", savedPublisher);
        log.info("tag: {}", savedTag);
        log.info("file: {}", savedFile);
        log.info("descript: {}", savedDescript);
    }

    @Test
    @Sql(scripts = {
            "classpath:scripts/publisher.sql",
            "classpath:scripts/author.sql",
            "classpath:scripts/info.sql",
            "classpath:scripts/book.sql",
            "classpath:scripts/tag.sql",
            "classpath:scripts/file.sql",
            "classpath:scripts/descript.sql"
    })
    void getBookTest() {
        Set<BookDTO> books = bookService.getAll();
        Assertions.assertNotNull(books, "books is null");
        Assertions.assertFalse(CollectionUtils.isEmpty(books), "books is empty");
        Assertions.assertEquals(1, books.size(), "books.size is not equals 1");

        Book dbBook = bookService.getEntity(books.stream().findFirst().map(BookDTO::getId).orElse(null));
        log.info("dbBook: {}", dbBook);
        Assertions.assertNotNull(dbBook.getDescripts(), "dbBook.descripts is null");
        Assertions.assertFalse(CollectionUtils.isEmpty(dbBook.getDescripts()), "dbBook.descripts is empty");
        Assertions.assertEquals(1, dbBook.getDescripts().size(), "dbBook.descripts is not equals 1");
        Descript dbDescript = dbBook.getDescripts().stream().findFirst().orElse(null);
        Assertions.assertNotNull(dbDescript, "dbDescript is null");
        Assertions.assertNotNull(dbDescript.getInfo(), "dbDescript.info is null");
        Assertions.assertNotNull(dbDescript.getInfo().getId(), "dbDescript.info.id is null");
        log.info("dbDescript.info.id: {}", dbDescript.getInfo().getId());
    }
}
