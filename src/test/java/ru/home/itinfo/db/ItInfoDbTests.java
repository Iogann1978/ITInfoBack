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
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.home.itinfo.config.JpaConfig;
import ru.home.itinfo.model.*;
import ru.home.itinfo.service.*;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
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
        Publisher publisher = Publisher.builder()
                .name("test publisher")
                .build();
        Publisher savedPublisher = publisherService.saveEntity(publisher);

        Tag tag = Tag.builder()
                .tag("Java")
                .build();
        Tag savedTag = tagService.saveEntity(tag);

        Author author = Author.builder()
                .name("test author")
                .build();
        Author savedAuthor = authorService.saveEntity(author);

        Book book = Book.builder()
                .isbn("1234567890")
                .title("Test book")
                .pages(3)
                .rate(Rate.GOOD)
                .state(State.PLANNED)
                .year(2007)
                .publisher(savedPublisher)
                .tags(Set.of(savedTag))
                .authors(Set.of(savedAuthor))
                .build();
        Book savedBook = bookService.saveEntity(book);
        Assertions.assertNotNull(savedBook, "savedBook is null");

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

        log.info("book: {}", savedBook);
        log.info("author: {}", savedAuthor);
        log.info("publisher: {}", savedPublisher);
        log.info("tag: {}", savedTag);
        log.info("file: {}", savedFile);
        log.info("descript: {}", savedDescript);

        TestTransaction.flagForCommit();
        TestTransaction.end();

        TestTransaction.start();
        Book derivedBook = bookService.getEntity(savedBook.getId());
        Assertions.assertNotNull(derivedBook, "derivedBook is null");
        log.info("derivedBook: {}", derivedBook);
        Assertions.assertNotNull(derivedBook.getPublisher(), "derivedBook.publisher is null");
        log.info("derivedBook.publisher: {}", derivedBook.getPublisher());
        Assertions.assertNotNull(derivedBook.getTags(), "derivedBook.tags is null");
        Assertions.assertFalse(CollectionUtils.isEmpty(derivedBook.getTags()), "derivedBook.tags is empty");
        Assertions.assertEquals(1, derivedBook.getTags().size(), "derivedBook.tags has more than 1 elements");
        log.info("derivedBook.tags: {}", derivedBook.getTags());
        Assertions.assertNotNull(derivedBook.getAuthors(), "derivedBook.authors is null");
        Assertions.assertFalse(CollectionUtils.isEmpty(derivedBook.getAuthors()), "derivedBook.authors is empty");
        Assertions.assertEquals(1, derivedBook.getAuthors().size(), "derivedBook.authors has more than 1 elements");
        log.info("derivedBook.authors: {}", derivedBook.getAuthors());
        Assertions.assertNotNull(derivedBook.getFile(), "derivedBook.file is null");
        log.info("derivedBook.file: {}", derivedBook.getFile());
        Assertions.assertNotNull(derivedBook.getDescripts(), "derivedBook.descripts is null");
        Assertions.assertFalse(CollectionUtils.isEmpty(derivedBook.getDescripts()), "derivedBook.descripts is empty");
        Assertions.assertEquals(1, derivedBook.getDescripts().size(), "derivedBook.descripts has more than 1 elements");
        log.info("derivedBook.descripts: {}", derivedBook.getDescripts());

        Publisher derivedPublisher = publisherService.getEntity(savedPublisher.getId());
        Assertions.assertNotNull(derivedPublisher, "derivedPublisher is null");
        log.info("derivedPublisher: {}", derivedPublisher);
        Assertions.assertEquals(derivedPublisher.getId(), derivedBook.getPublisher().getId(), "ids publishers is not equal");
        Assertions.assertNotNull(derivedPublisher.getInfos(), "derivedPublisher.infos is null");
        Assertions.assertFalse(CollectionUtils.isEmpty(derivedPublisher.getInfos()), "derivedPublisher.infos is empty");

        Tag derivedTag = tagService.getEntity(savedTag.getTag());
        Assertions.assertNotNull(derivedTag, "derivedTag is null");
        derivedBook.getTags().stream().findFirst().ifPresent(t -> Assertions.assertEquals(t.getTag(), derivedTag.getTag(), "ids tags not equal"));

        Author derivedAuthor = authorService.getEntity(savedAuthor.getId());
        Assertions.assertNotNull(derivedAuthor, "derivedAuthor is null");
        derivedBook.getAuthors().stream().findFirst().ifPresent(a -> Assertions.assertEquals(a.getId(), derivedAuthor.getId(), "ids authors not equal"));

        InfoFile derivedFile = fileService.getEntity(savedFile.getId());
        Assertions.assertNotNull(derivedFile, "derivedFile is null");
        Assertions.assertEquals(savedFile.getId(), derivedFile.getId(), "ids files not equal");
        Assertions.assertNotNull(derivedFile.getInfo(), "derivedFile.info is null");

        Descript derivedDescript = descriptService.getEntity(savedDescript.getId());
        Assertions.assertNotNull(derivedDescript, "derivedDescript is null");
        Assertions.assertEquals(savedDescript.getId(), derivedDescript.getId(), "ids descripts not equal");
        Assertions.assertNotNull(derivedDescript.getInfo(), "derivedDescript.info is null");
    }

    /**
     * Проверка удаления описания. При удалении описания, оно должно удаляться из книги или курса
     */
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
    void descriptDeleteTest() {
        descriptService.delete(5L);
        TestTransaction.flagForCommit();
        TestTransaction.end();

        TestTransaction.start();
        Book dbBook = bookService.getEntity(1L);
        Assertions.assertNotNull(dbBook, "dbBook is null");
        log.info("dbBook: {}", dbBook);
        Assertions.assertTrue(CollectionUtils.isEmpty(dbBook.getDescripts()), "dbBook.descripts is not empty");
    }

    /**
     * Проверка добавления нового описания в книгу и сохранение книги вместе с описанием
     */
    @Test
    void descriptNewSaveTest() {
        Descript descript = Descript.builder()
                .name("test descript")
                .text("Test".getBytes(StandardCharsets.UTF_8))
                .build();
        Publisher publisher = Publisher.builder()
                .name("test publisher")
                .build();
        Book book = Book.builder()
                .isbn("1234567890")
                .title("Test book")
                .pages(3)
                .rate(Rate.GOOD)
                .state(State.PLANNED)
                .year(2007)
                .publisher(publisher)
                .descripts(Set.of(descript))
                .build();
        descript.setInfo(book);
        Book savedBook = bookService.saveEntity(book);
        TestTransaction.flagForCommit();
        TestTransaction.end();

        TestTransaction.start();
        Book derivedBook = bookService.getEntity(savedBook.getId());
        Assertions.assertNotNull(derivedBook, "derivedBook is null");
        log.info("derivedBook: {}", derivedBook);
        Assertions.assertNotNull(derivedBook.getDescripts(), "derivedBook.descripts is null");
        Assertions.assertFalse(CollectionUtils.isEmpty(derivedBook.getDescripts()), "derivedBook.descripts is empty");
        Assertions.assertEquals(1, derivedBook.getDescripts().size(), "derivedBook.descripts is not equals 1");
        log.info("derivedBook.descripts: {}", derivedBook.getDescripts());
        Descript derivedDescript = derivedBook.getDescripts().stream().findFirst().orElse(null);
        Assertions.assertNotNull(derivedDescript, "derivedDescript is null");
        Assertions.assertNotNull(derivedDescript.getId(), "derivedDescript.id is null");
        Assertions.assertNotNull(derivedDescript.getInfo(), "derivedDescript.info is null");
        Assertions.assertNotNull(derivedDescript.getInfo().getId(), "derivedDescript.info.id is null");
        log.info("derivedDescript: {}", derivedDescript);
    }

    /**
     * Проверка удаления файла
     */
    @Test
    @Sql(scripts = {
            "classpath:scripts/publisher.sql",
            "classpath:scripts/author.sql",
            "classpath:scripts/info.sql",
            "classpath:scripts/book.sql",
            "classpath:scripts/tag.sql",
            "classpath:scripts/file.sql"
    })
    void fileDeleteTest() {
        fileService.delete(4L);
        TestTransaction.flagForCommit();
        TestTransaction.end();

        TestTransaction.start();
        Book dbBook = bookService.getEntity(1L);
        Assertions.assertNotNull(dbBook, "dbBook is null");
        log.info("dbBook: {}", dbBook);
        Assertions.assertNull(dbBook.getFile(), "dbBook.file is not empty");
    }

    /**
     * Проверка удаления тэга
     */
    @Test
    @Sql(scripts = {
            "classpath:scripts/publisher.sql",
            "classpath:scripts/author.sql",
            "classpath:scripts/info.sql",
            "classpath:scripts/book.sql",
            "classpath:scripts/tag.sql"
    })
    void tagDeleteTest() {
        Book book = bookService.getEntity(1L);
        Assertions.assertNotNull(book, "book is null");
        Assertions.assertFalse(CollectionUtils.isEmpty(book.getTags()), "book.tags is empty");
        Assertions.assertEquals(1, book.getTags().size(), "book has mothe than 1 tag");
        book.setTags(Collections.EMPTY_SET);
        bookService.saveEntity(book);
        tagService.delete("Java");
        TestTransaction.flagForCommit();
        TestTransaction.end();

        TestTransaction.start();
        Book dbBook = bookService.getEntity(1L);
        Assertions.assertNotNull(dbBook, "dbBook is null");
        log.info("dbBook: {}", dbBook);
        Assertions.assertTrue(CollectionUtils.isEmpty(dbBook.getTags()), "dbBook.tags is not empty");
    }
}
