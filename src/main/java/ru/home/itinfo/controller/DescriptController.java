package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.service.DescriptService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/descript")
@Tag(name = "Контроллер работы с описанием")
public class DescriptController {
    private DescriptService descriptService;

    @GetMapping("/book/{id}")
    @Operation(summary = "Получить описание книги")
    public String getFromBook(
            @Parameter(description = "id книги")
            @PathVariable Long bookId
    ) {
        return descriptService.getFromBook(bookId);
    }

    @GetMapping("/course/{id}")
    @Operation(summary = "Получить описание курса")
    public String getFromCourse(
            @Parameter(description = "id курса")
            @PathVariable Long courseId
    ) {
        return descriptService.getFromCourse(courseId);
    }
}
