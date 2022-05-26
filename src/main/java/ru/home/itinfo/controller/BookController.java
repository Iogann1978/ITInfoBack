package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.dto.BookDTO;
import ru.home.itinfo.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
@Tag(name = "BookController", description = "Контроллер работы с книгами")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Получить список книг")
    public List<BookDTO> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить книгу")
    public BookDTO get(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        return bookService.get(id);
    }

    @PostMapping
    @Operation(summary = "Сохранить книгу")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @Parameter(description = "Книга")
            @RequestBody BookDTO bookDTO
    ) {
        bookService.merge(bookDTO);
        bookService.save(bookDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить книгу")
    public void delete(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        bookService.delete(id);
    }
}
