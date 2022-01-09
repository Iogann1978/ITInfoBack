package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.itinfo.dto.BookDTO;
import ru.home.itinfo.service.GoogleService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/google")
@Tag(name = "Контроллер работы с сервисом Google")
@Slf4j
public class GoogleController {
    private final GoogleService googleService;

    @GetMapping("/{isbn}")
    @Operation(summary = "Поиск описания книги по isbn")
    public BookDTO get(
            @Parameter(description = "isbn")
            @PathVariable String isbn
    ) {
        log.debug("GoogleController get: {}", isbn);
        return googleService.get(isbn);
    }
}
