package ru.home.itinfo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import ru.home.itinfo.model.Author;
import ru.home.itinfo.model.Book;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Slf4j
public class BookEntityListener {
    @PrePersist
    @PreUpdate
    public void prepersist(Book book) {
        log.info("prepersist book");
        if (!CollectionUtils.isEmpty(book.getAuthors())) {
            for (Author a : book.getAuthors()) {
                a.addBook(book);
                log.info("a: {}", a);
            }
        }
    }
}
