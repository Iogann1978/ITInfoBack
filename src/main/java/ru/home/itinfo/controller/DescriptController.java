package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.home.itinfo.service.DescriptService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/descript")
@Tag(name = "DescriptController", description = "Контроллер работы с описанием")
@Slf4j
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

    @PostMapping("/{id}")
    @Operation(summary = "Обновить описание")
    public void descriptUpload(
            @Parameter(description = "id описания")
            @PathVariable Long id,
            @Parameter(description = "Содержимое файла")
            @RequestParam("file") MultipartFile file
    ) {
        try {
            log.info("file: {} {}", id, new String(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
