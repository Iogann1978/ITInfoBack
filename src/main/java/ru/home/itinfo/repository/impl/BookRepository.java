package ru.home.itinfo.repository.impl;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Book;
import ru.home.itinfo.repository.CommonRepository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends CommonRepository<Book, Long> {
    @EntityGraph(attributePaths = {"publisher", "tags", "authors", "descripts", "file"})
    Optional<Book> findById(Long id);
    @EntityGraph(attributePaths = {"publisher", "tags", "authors", "descripts", "file"})
    Book getById(Long id);
    @EntityGraph(attributePaths = {"publisher", "tags", "authors", "descripts", "file"})
    @Query("select b from Book b order by b.title")
    Set<Book> getListOrdered();
}
