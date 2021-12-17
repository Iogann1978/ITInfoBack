package ru.home.itinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
