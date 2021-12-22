package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.dto.PublisherDTO;
import ru.home.itinfo.service.PublisherService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/publisher")
public class PublisherController {
    private PublisherService publisherService;

    @GetMapping
    @Operation(summary = "Получить список издателей")
    public List<PublisherDTO> getAll() {
        return publisherService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить издателя")
    public PublisherDTO get(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        return publisherService.get(id);
    }

    @PostMapping
    @Operation(summary = "Сохранить издателя")
    public void save(
            @Parameter(description = "Издатель")
            @RequestBody PublisherDTO publisherDTO
    ) {
        publisherService.save(publisherDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить издателя")
    public void delete(
            @Parameter(description = "id")
            @PathVariable Long id
    ) {
        publisherService.delete(id);
    }
}
