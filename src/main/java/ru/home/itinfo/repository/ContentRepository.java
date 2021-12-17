package ru.home.itinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
}
