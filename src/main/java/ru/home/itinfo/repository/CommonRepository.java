package ru.home.itinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommonRepository<T, ID> extends JpaRepository<T, ID> {
    Optional<T> findById(ID id);
    T getById(ID id);
    List<T> getListOrdered();
}
