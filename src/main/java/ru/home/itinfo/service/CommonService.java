package ru.home.itinfo.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class CommonService<T, ID> {
    private final JpaRepository<T, ID> repository;

    protected CommonService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public void save(T t) {
        repository.save(t);
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }
}
