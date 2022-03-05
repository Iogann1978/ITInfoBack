package ru.home.itinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByTag(String tag);
}
