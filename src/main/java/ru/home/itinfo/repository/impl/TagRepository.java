package ru.home.itinfo.repository.impl;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Tag;
import ru.home.itinfo.repository.CommonRepository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TagRepository extends CommonRepository<Tag, String> {
    @EntityGraph(attributePaths = {"infos"})
    Optional<Tag> findById(String tag);
    @EntityGraph(attributePaths = {"infos"})
    Tag getById(String tag);
    @EntityGraph(attributePaths = {"infos"})
    @Query("select t from Tag t order by t.tag")
    Set<Tag> getListOrdered();
}
