package ru.home.itinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CommonRepository<T, ID> extends JpaRepository<T, ID> {
    Optional<T> findById(ID id);
    T getById(ID id);
    Set<T> getListOrdered();
}
