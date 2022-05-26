package ru.home.itinfo.repository.impl;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Author;
import ru.home.itinfo.repository.CommonRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CommonRepository<Author, Long> {
    @EntityGraph(attributePaths = {"books"})
    Optional<Author> findById(Long id);
    @EntityGraph(attributePaths = {"books"})
    Author getById(Long id);
    @EntityGraph(attributePaths = {"books"})
    Optional<Author> findFirstByName(String name);
    @EntityGraph(attributePaths = {"books"})
    @Query("select distinct a from Author a left join fetch a.books order by a.name")
    List<Author> getListOrdered();
}
