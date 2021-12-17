package ru.home.itinfo.controller;

import org.springframework.web.bind.annotation.*;
import ru.home.itinfo.model.Author;
import ru.home.itinfo.service.CommonService;

import java.util.List;

public abstract class CommonController<T, ID> {
    private final CommonService<T, ID> service;

    protected CommonController(CommonService<T, ID> service) {
        this.service = service;
    }

    @GetMapping
    public List<T> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void save(@RequestBody T t) {
        service.save(t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        service.delete(id);
    }
}
