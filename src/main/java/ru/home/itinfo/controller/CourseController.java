package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.dto.CourseDTO;
import ru.home.itinfo.service.CourseService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;

    @GetMapping
    @Operation(summary = "Получить список курсов")
    public List<CourseDTO> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить курс")
    public CourseDTO get(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        return courseService.get(id);
    }

    @PostMapping
    @Operation(summary = "Сохранить курс")
    public void save(
            @Parameter(description = "Курс")
            @RequestBody CourseDTO courseDTO
    ) {
        courseService.save(courseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить курс")
    public void delete(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        courseService.delete(id);
    }
}
