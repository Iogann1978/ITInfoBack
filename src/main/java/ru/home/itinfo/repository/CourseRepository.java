package ru.home.itinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
