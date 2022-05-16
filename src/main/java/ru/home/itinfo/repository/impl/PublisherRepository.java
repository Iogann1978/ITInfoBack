package ru.home.itinfo.repository.impl;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Publisher;
import ru.home.itinfo.repository.CommonRepository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PublisherRepository extends CommonRepository<Publisher, Long> {
    @EntityGraph(attributePaths = {"infos"})
    Optional<Publisher> findFirstByName(String name);
    @EntityGraph(attributePaths = {"infos"})
    Optional<Publisher> findById(Long id);
    @EntityGraph(attributePaths = {"infos"})
    Publisher getById(Long id);
    @EntityGraph(attributePaths = {"infos"})
    @Query("select p from Publisher p order by p.name")
    Set<Publisher> getListOrdered();
}
