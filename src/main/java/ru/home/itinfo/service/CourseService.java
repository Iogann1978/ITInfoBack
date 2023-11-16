package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.CourseDTO;
import ru.home.itinfo.dto.FindDTO;
import ru.home.itinfo.mapper.CourseMapper;
import ru.home.itinfo.mapper.FindMapper;
import ru.home.itinfo.model.Course;
import ru.home.itinfo.repository.impl.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService extends CommonService<CourseDTO, Course, Long> {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final FindMapper findMapper;

    @Autowired
    public CourseService(
            CourseRepository courseRepository,
            CourseMapper courseMapper,
            FindMapper findMapper) {
        super(courseRepository, courseMapper, "Курс");
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.findMapper = findMapper;
    }

    public List<FindDTO> findByTitle(String title) {
        return courseRepository.getAllByTitleLike(title).stream()
                .map(courseMapper::entityToDto)
                .map(findMapper::fromInfoDTO).collect(Collectors.toList());
    }

    public boolean existsById(Long id) {
        return courseRepository.existsById(id);
    }
}
