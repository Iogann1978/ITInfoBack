package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.dto.InfoDTO;
import ru.home.itinfo.service.InfoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/info")
@Tag(name = "InfoController", description = "Контроллер работы с общей информацией")
public class InfoController {
    private final InfoService infoService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить информацию")
    public InfoDTO get(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        return infoService.get(id);
    }
}
