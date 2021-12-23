package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.DescriptDTO;
import ru.home.itinfo.mapper.DescriptMapper;
import ru.home.itinfo.model.Descript;
import ru.home.itinfo.repository.DescriptRepository;

@Service
public class DescriptService extends CommonService<DescriptDTO, Descript, Long> {
    @Autowired
    public DescriptService(DescriptRepository descriptRepository, DescriptMapper descriptMapper) {
        super(descriptRepository, descriptMapper, "Описание");
    }
}
