package ru.home.itinfo.repository.impl;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Publisher;
import ru.home.itinfo.repository.CommonRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends CommonRepository<Publisher, Long> {
    @EntityGraph(attributePaths = {"infos"})
    Optional<Publisher> findFirstByName(String name);
    @EntityGraph(attributePaths = {"infos"})
    Optional<Publisher> findById(Long id);
    @EntityGraph(attributePaths = {"infos"})
    Publisher getById(Long id);
    @EntityGraph(attributePaths = {"infos"})
    @Query("select distinct p from Publisher p left join fetch p.infos order by p.name")
    List<Publisher> getListOrdered();
    @EntityGraph(attributePaths = {"infos"})
    List<Publisher> getAllByName(String publisher);
}
