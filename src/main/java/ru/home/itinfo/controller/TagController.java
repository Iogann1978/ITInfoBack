package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.dto.TagDTO;
import ru.home.itinfo.service.TagService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tag")
@Tag(name = "Контроллер работы с тэгами")
public class TagController {
    private final TagService tagService;

    @GetMapping
    @Operation(summary = "Получить список тэгов")
    public List<TagDTO> getAll() {
        return tagService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить тэг")
    public TagDTO get(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        return tagService.get(id);
    }

    @PostMapping
    @Operation(summary = "Сохранить тэг")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @Parameter(description = "Тэг")
            @RequestBody TagDTO tagDTO
    ) {
        tagService.save(tagDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить тэг")
    public void delete(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        tagService.delete(id);
    }
}
