package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.FindDTO;
import ru.home.itinfo.dto.PublisherDTO;
import ru.home.itinfo.mapper.PublisherMapper;
import ru.home.itinfo.model.Publisher;
import ru.home.itinfo.repository.impl.PublisherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService extends CommonService<PublisherDTO, Publisher, Long> {
    private final PublisherRepository publisherRepository;
    private final BookService bookService;

    @Autowired
    public PublisherService(
            PublisherRepository publisherRepository,
            PublisherMapper publisherMapper,
            BookService bookService) {
        super(publisherRepository, publisherMapper, "Издатель");
        this.publisherRepository = publisherRepository;
        this.bookService = bookService;
    }

    public Publisher getPublisher(String name) {
        return publisherRepository.findFirstByName(name)
                .orElseGet(() -> saveEntity(Publisher.builder().name(name).build()));
    }

    public List<FindDTO> findByPublisher(String publisher) {
        return publisherRepository.getAllByName(publisher).stream()
                .flatMap(p -> p.getInfos().stream())
                .map(info ->
                        FindDTO.builder()
                                .title(info.getTitle())
                                .type(bookService.existsById(info.getId()) ? FindDTO.FindType.BOOK : FindDTO.FindType.COURSE)
                                .build()
                ).collect(Collectors.toList());
    }
}
