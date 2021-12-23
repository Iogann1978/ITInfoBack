package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.home.itinfo.dto.google.VolumeDTO;

import java.net.URI;

@Service
public class GoogleService {
    @Value("${google.api.host}")
    private String googleApiHost;
    @Value("${google.api.path}")
    private String googleApiPath;
    private final RestTemplate restTemplate;

    @Autowired
    public  GoogleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public VolumeDTO get(String isbn) {
        URI uri = UriComponentsBuilder.newInstance().scheme("https")
                .host(googleApiHost).path(googleApiPath).query("q=isbn:{isbn}").build(isbn);
        return restTemplate.getForObject(uri, VolumeDTO.class);
    }
}
