package ru.home.itinfo.repository.impl;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.InfoFile;
import ru.home.itinfo.repository.CommonRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends CommonRepository<InfoFile, Long> {
    @EntityGraph(attributePaths = {"info"})
    Optional<InfoFile> findById(Long id);
    @EntityGraph(attributePaths = {"info"})
    InfoFile getById(Long id);
    @EntityGraph(attributePaths = {"info"})
    @Query("select f from InfoFile f order by f.filename")
    List<InfoFile> getListOrdered();
}
