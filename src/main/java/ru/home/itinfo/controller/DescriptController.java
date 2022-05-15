package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.home.itinfo.dto.DescriptDTO;
import ru.home.itinfo.service.DescriptService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/descript")
@Tag(name = "DescriptController", description = "Контроллер работы с описанием")
@Slf4j
public class DescriptController {
    private final DescriptService descriptService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить описание")
    public DescriptDTO get(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        return descriptService.get(id);
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

    @PostMapping
    @Operation(summary = "Сохранить описание")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @Parameter(description = "Описание")
            @RequestBody DescriptDTO descriptDTO
    ) {
        descriptService.save(descriptDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить описание")
    public void delete(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        descriptService.delete(id);
    }
}
