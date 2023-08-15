package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.InfoDTO;
import ru.home.itinfo.dto.PublisherDTO;
import ru.home.itinfo.mapper.InfoMapper;
import ru.home.itinfo.mapper.PublisherMapper;
import ru.home.itinfo.model.Publisher;
import ru.home.itinfo.repository.impl.PublisherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService extends CommonService<PublisherDTO, Publisher, Long> {
    private final PublisherRepository publisherRepository;
    private final InfoMapper infoMapper;

    @Autowired
    public PublisherService(
            PublisherRepository publisherRepository,
            PublisherMapper publisherMapper,
            InfoMapper infoMapper) {
        super(publisherRepository, publisherMapper, "Издатель");
        this.publisherRepository = publisherRepository;
        this.infoMapper = infoMapper;
    }

    public Publisher getPublisher(String name) {
        return publisherRepository.findFirstByName(name)
                .orElseGet(() -> saveEntity(Publisher.builder().name(name).build()));
    }

    public List<InfoDTO> findByPublisher(String publisher) {
        return publisherRepository.getAllByNameLike(publisher).stream()
                .flatMap(p -> p.getInfos().stream()).map(infoMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
