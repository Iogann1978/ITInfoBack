package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.dto.AuthorDTO;
import ru.home.itinfo.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @Operation(summary = "Получить список всех авторов", description = "Получить список всех авторов")
    public List<AuthorDTO> getAll() {
        return authorService.getAll();
    }

    @PostMapping
    @Operation(summary = "Сохранить автора", description = "Сохранить автора")
    public void save(@RequestBody AuthorDTO authorDTO) {
        authorService.save(authorDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить автора", description = "Удалить автора")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
