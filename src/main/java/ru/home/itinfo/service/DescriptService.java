package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.model.Descript;
import ru.home.itinfo.repository.DescriptRepository;

@Service
public class DescriptService extends CommonService<Descript, Long> {
    @Autowired
    public DescriptService(DescriptRepository descriptRepository) {
        super(descriptRepository);
    }
}
