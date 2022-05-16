package ru.home.itinfo.repository.impl;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Course;
import ru.home.itinfo.repository.CommonRepository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CourseRepository extends CommonRepository<Course, Long> {
    @EntityGraph(attributePaths = {"publisher", "tags", "descripts", "file"})
    Optional<Course> findById(Long id);
    @EntityGraph(attributePaths = {"publisher", "tags", "descripts", "file"})
    Course getById(Long id);
    @EntityGraph(attributePaths = {"publisher", "tags", "descripts", "file"})
    @Query("select c from Course c order by c.title")
    Set<Course> getListOrdered();
}
