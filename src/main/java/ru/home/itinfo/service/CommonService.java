package ru.home.itinfo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import ru.home.itinfo.exception.NotFoundException;
import ru.home.itinfo.mapper.CommonMapper;
import ru.home.itinfo.repository.CommonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class CommonService<T1, T2, ID> {
    private final CommonRepository<T2, ID> repository;
    private final CommonMapper<T1, T2> commonMapper;
    private final String entityName;

    protected CommonService(
            CommonRepository<T2, ID> repository,
            CommonMapper<T1, T2> commonMapper,
            String entityName) {
        this.repository = repository;
        this.commonMapper = commonMapper;
        this.entityName = entityName;
    }

    @Transactional(readOnly = true)
    public List<T1> getAll() {
        List<T2> list = repository.getListOrdered();
        return list.stream().map(commonMapper::entityToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public T1 get(ID id) {
        T2 t2 = repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("%s с id=%d не найден", entityName, id)));
        return commonMapper.entityToDto(t2);
    }

    @Transactional(readOnly = true)
    public T2 getEntity(ID id) {
        return repository.getById(id);
    }

    @Transactional
    public void save(T1 t1) {
        T2 t2 = commonMapper.dtoToEntity(t1);
        repository.save(t2);
    }

    @Transactional
    public T2 saveEntity(T2 t2) {
        return repository.save(t2);
    }

    @Transactional
    public void delete(ID id) {
        repository.deleteById(id);
    }
}
