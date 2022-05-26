package ru.home.itinfo.repository.impl;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Descript;
import ru.home.itinfo.repository.CommonRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DescriptRepository extends CommonRepository<Descript, Long> {
    @EntityGraph(attributePaths = {"info"})
    Optional<Descript> findById(Long id);
    @EntityGraph(attributePaths = {"info"})
    Descript getById(Long id);
    @EntityGraph(attributePaths = {"info"})
    @Query("select d from Descript d order by d.name")
    List<Descript> getListOrdered();
}
