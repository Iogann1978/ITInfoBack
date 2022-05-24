package ru.home.itinfo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.home.itinfo.dto.*;
import ru.home.itinfo.dto.google.VolumeDTO;
import ru.home.itinfo.exception.NotFoundException;
import ru.home.itinfo.mapper.AuthorMapper;
import ru.home.itinfo.mapper.PublisherMapper;
import ru.home.itinfo.mapper.TagMapper;
import ru.home.itinfo.mapper.VolumeMapper;
import ru.home.itinfo.model.Publisher;

import java.net.URI;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleService {
    @Value("${google.api.host}")
    private String googleApiHost;
    @Value("${google.api.path}")
    private String googleApiPath;
    private final RestTemplate restTemplate;
    private final VolumeMapper volumeMapper;
    private final PublisherMapper publisherMapper;
    private final AuthorMapper authorMapper;
    private final TagMapper tagMapper;
    private final PublisherService publisherService;
    private final AuthorService authorService;
    private final TagService tagService;

    public BookDTO get(String isbn) throws NotFoundException {
        log.info("isbn old: {}", isbn);
        isbn = isbn.trim().replace("-","");
        log.info("isbn new: {}", isbn);
        URI uri = UriComponentsBuilder.newInstance().scheme("https")
                .host(googleApiHost).path(googleApiPath).query("q=isbn:{isbn}").build(isbn);
        VolumeDTO volumeDTO = restTemplate.getForObject(uri, VolumeDTO.class);
        if (volumeDTO == null || CollectionUtils.isEmpty(volumeDTO.getItems())) {
            throw new NotFoundException(String.format("Книга по isbn %s не найдена", isbn));
        }
        volumeDTO.getItems().get(0).getVolumeInfo().setIsbn(isbn);
        BookDTO bookDTO = volumeMapper.volumeToBook(volumeDTO.getItems().get(0).getVolumeInfo());
        bookDTO.setRate(RateDTO.UNKNOWN);
        bookDTO.setState(StateDTO.PLANNED);
        Optional.ofNullable(bookDTO.getPublisher())
                .map(PublisherDTO::getName).ifPresent(name -> {
                    Publisher publisher = publisherService.getPublisher(name);
                    PublisherDTO publisherDTO = publisherMapper.entityToDto(publisher);
                    bookDTO.setPublisher(publisherDTO);
                });
        /*
        if (!CollectionUtils.isEmpty(bookDTO.getAuthors())) {
            Set<AuthorDTO> authors = bookDTO.getAuthors().stream()
                    .filter(author -> author != null && !author.getName().isEmpty())
                    .map(AuthorDTO::getName)
                    .map(name -> authorService.getAuthor(name))
                    .map(authorMapper::entityToDto).collect(Collectors.toSet());
            bookDTO.setAuthors(authors);
        }
         */
        if (!CollectionUtils.isEmpty(bookDTO.getTags())) {
            Set<TagDTO> tags = bookDTO.getTags().stream()
                    .filter(tag -> tag != null && !tag.getTag().isEmpty())
                    .map(TagDTO::getTag)
                    .map(tag -> tagService.getTag(tag))
                    .map(tagMapper::entityToDto).collect(Collectors.toSet());
            bookDTO.setTags(tags);
        }
        return bookDTO;
    }
}
