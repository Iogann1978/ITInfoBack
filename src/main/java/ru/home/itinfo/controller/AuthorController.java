package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.dto.AuthorDTO;
import ru.home.itinfo.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
@Tag(name = "AuthorController", description = "Контроллер работы с авторами")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    @Operation(summary = "Получить список авторов")
    public List<AuthorDTO> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить автора")
    public AuthorDTO get(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        return authorService.get(id);
    }

    @PostMapping
    @Operation(summary = "Сохранить автора")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @Parameter(description = "Автор")
            @RequestBody AuthorDTO authorDTO
    ) {
        authorService.save(authorDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить автора")
    public void delete(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        authorService.delete(id);
    }
}
