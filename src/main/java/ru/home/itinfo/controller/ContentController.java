package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.dto.ContentDTO;
import ru.home.itinfo.service.ContentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/content")
@Tag(name = "Контроллер работы с содержанием")
public class ContentController {
    private final ContentService contentService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить содержание")
    public ContentDTO get(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        return contentService.get(id);
    }

    @PostMapping
    @Operation(summary = "Сохранить содержание")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @Parameter(description = "Содержание")
            @RequestBody ContentDTO contentDTO
    ) {
        contentService.save(contentDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить содержание")
    public void delete(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        contentService.delete(id);
    }
}
