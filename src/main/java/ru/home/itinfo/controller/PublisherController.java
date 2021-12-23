package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.dto.PublisherDTO;
import ru.home.itinfo.service.PublisherService;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {
    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

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
    @ResponseStatus(HttpStatus.CREATED)
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
