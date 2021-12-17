package ru.home.itinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.InfoFile;

@Repository
public interface FileRepository extends JpaRepository<InfoFile, Long> {
}
