package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import ru.home.itinfo.dto.CourseDTO;
import ru.home.itinfo.model.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper extends CommonMapper<CourseDTO, Course> {
}
