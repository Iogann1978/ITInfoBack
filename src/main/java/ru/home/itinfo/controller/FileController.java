package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.dto.InfoFileDTO;
import ru.home.itinfo.service.FileService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
@Tag(name = "FileController", description = "Контроллер работы с файлами")
public class FileController {
    private final FileService fileService;

    @GetMapping
    @Operation(summary = "Получить список файлов")
    public List<InfoFileDTO> getAll() {
        return fileService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить файл")
    public InfoFileDTO get(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        return fileService.get(id);
    }

    @PostMapping
    @Operation(summary = "Сохранить файл")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @Parameter(description = "Файл")
            @RequestBody InfoFileDTO fileDTO
    ) {
        fileService.save(fileDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить автора")
    public void delete(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        fileService.delete(id);
    }
}
