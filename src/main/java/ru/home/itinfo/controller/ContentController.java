package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.home.itinfo.dto.ContentDTO;
import ru.home.itinfo.service.ContentService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/content")
@Tag(name = "ContentController", description = "Контроллер работы с содержанием")
@Slf4j
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

    @PostMapping("/{id}")
    @Operation(summary = "Обновить содержание")
    public void contentUpload(
            @Parameter(description = "id содержания")
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
