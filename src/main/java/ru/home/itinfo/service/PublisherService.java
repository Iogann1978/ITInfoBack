package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.PublisherDTO;
import ru.home.itinfo.mapper.PublisherMapper;
import ru.home.itinfo.model.Publisher;
import ru.home.itinfo.repository.PublisherRepository;

@Service
public class PublisherService extends CommonService<PublisherDTO, Publisher, Long> {
    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        super(publisherRepository, publisherMapper, "Издатель");
        this.publisherRepository = publisherRepository;
    }

    public Publisher getPublisher(String name) {
        return publisherRepository.findFirstByName(name)
                .orElseGet(() -> saveEntity(Publisher.builder().name(name).build()));
    }
}
