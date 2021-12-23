package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.dto.ContentDTO;
import ru.home.itinfo.service.ContentService;

@RestController
@RequestMapping("/content")
public class ContentController {
    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

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
