package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.dto.CourseDTO;
import ru.home.itinfo.service.CourseService;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
@Tag(name = "CourseController", description = "Контроллер работы с курсами")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    @Operation(summary = "Получить список курсов")
    public Set<CourseDTO> getAll() {
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
    @ResponseStatus(HttpStatus.CREATED)
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
