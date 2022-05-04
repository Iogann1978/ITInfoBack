package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.itinfo.service.InfoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
@Tag(name = "InfoController", description = "Контроллер работы с общей информацией")
public class InfoController {
    private final InfoService infoService;

    @GetMapping("/title/{id}")
    @Operation(summary = "Получить название")
    public String get(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        return infoService.get(id).getTitle();
    }
}
