package ru.home.itinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.itinfo.dto.google.VolumeDTO;
import ru.home.itinfo.service.GoogleService;

@RestController
@RequestMapping("/google")
public class GoogleController {
    private final GoogleService googleService;

    @Autowired
    public GoogleController(GoogleService googleService) {
        this.googleService = googleService;
    }

    @GetMapping("/{isbn}")
    public VolumeDTO get(@PathVariable String isbn) {
        return googleService.get(isbn);
    }
}
