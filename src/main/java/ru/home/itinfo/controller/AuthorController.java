package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.dto.AuthorDTO;
import ru.home.itinfo.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    private AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

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
