package ru.home.itinfo.repository.impl;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Descript;
import ru.home.itinfo.repository.CommonRepository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DescriptRepository extends CommonRepository<Descript, Long> {
    @EntityGraph(attributePaths = {"info"})
    Optional<Descript> findById(Long id);
    @EntityGraph("descript")
    Descript getById(Long id);
    @EntityGraph("descripts")
    @Query("select d from Descript d order by d.name")
    Set<Descript> getListOrdered();
}
