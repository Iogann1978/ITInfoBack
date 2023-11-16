package ru.home.itinfo.repository.impl;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Book;
import ru.home.itinfo.repository.CommonRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CommonRepository<Book, Long> {
    @EntityGraph(attributePaths = {"publisher", "tags", "authors", "descripts", "file"})
    Optional<Book> findById(Long id);
    @EntityGraph(attributePaths = {"publisher", "tags", "authors", "descripts", "file"})
    Book getById(Long id);
    @EntityGraph(attributePaths = {"publisher", "tags", "authors", "descripts", "file"})
    @Query("select distinct b from Book b left join fetch b.tags left join fetch b.authors left join fetch b.descripts order by b.title")
    List<Book> getListOrdered();
    @EntityGraph(attributePaths = {"publisher", "tags", "authors", "descripts", "file"})
    List<Book> findAllByIsbn(String isbn);
    @EntityGraph(attributePaths = {"publisher", "tags", "descripts", "file"})
    List<Book> getAllByTitleLike(String title);
}
