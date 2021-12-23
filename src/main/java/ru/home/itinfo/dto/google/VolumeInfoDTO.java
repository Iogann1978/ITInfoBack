package ru.home.itinfo.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class VolumeInfoDTO {
    @JsonProperty
    private String title;
    @JsonProperty
    private String subtitle;
    @JsonProperty
    private List<String> authors;
    @JsonProperty
    private String publisher;
    @JsonProperty
    private LocalDate publishedDate;
    @JsonProperty
    private String description;
}
