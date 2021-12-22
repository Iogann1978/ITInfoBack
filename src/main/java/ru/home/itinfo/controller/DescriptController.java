package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.dto.DescriptDTO;
import ru.home.itinfo.service.DescriptService;

@RestController
@AllArgsConstructor
@RequestMapping("/descript")
public class DescriptController {
    private DescriptService descriptService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить описание")
    public DescriptDTO get(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        return descriptService.get(id);
    }

    @PostMapping
    @Operation(summary = "Сохранить описание")
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
