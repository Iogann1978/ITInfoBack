package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.CourseDTO;
import ru.home.itinfo.mapper.CourseMapper;
import ru.home.itinfo.model.Course;
import ru.home.itinfo.repository.CourseRepository;

@Service
public class CourseService extends CommonService<CourseDTO, Course, Long> {
    @Autowired
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        super(courseRepository, courseMapper, "Курс");
    }
}
