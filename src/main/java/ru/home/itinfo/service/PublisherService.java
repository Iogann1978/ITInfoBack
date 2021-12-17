package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.model.Publisher;
import ru.home.itinfo.repository.PublisherRepository;

@Service
public class PublisherService extends CommonService<Publisher, Long> {
    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        super(publisherRepository);
    }
}
