package ru.home.itinfo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.home.itinfo.mapper.CommonMapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class CommonService<T1, T2, ID> {
    private final JpaRepository<T2, ID> repository;
    private final CommonMapper<T1, T2> commonMapper;

    protected CommonService(JpaRepository<T2, ID> repository, CommonMapper<T1, T2> commonMapper) {
        this.repository = repository;
        this.commonMapper = commonMapper;
    }

    public List<T1> getAll() {
        List<T2> list2 = repository.findAll();
        return list2.stream().map(commonMapper::entityToDto).collect(Collectors.toList());
    }

    public T1 get(ID id) {
        T2 t2 = repository.getById(id);
        return commonMapper.entityToDto(t2);
    }

    public void save(T1 t1) {
        T2 t2 = commonMapper.dtoToEntity(t1);
        repository.save(t2);
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }
}
