package ru.home.itinfo.repository.impl;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Info;
import ru.home.itinfo.repository.CommonRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface InfoRepository extends CommonRepository<Info, Long> {
    @EntityGraph(attributePaths = {"publisher", "tags", "descripts", "file"})
    Optional<Info> findById(Long id);
    @EntityGraph(attributePaths = {"publisher", "tags", "descripts", "file"})
    Info getById(Long id);
    @EntityGraph(attributePaths = {"publisher", "tags", "descripts", "file"})
    @Query("select distinct i from Info i left join fetch i.tags left join fetch i.descripts order by i.title")
    List<Info> getListOrdered();
}
