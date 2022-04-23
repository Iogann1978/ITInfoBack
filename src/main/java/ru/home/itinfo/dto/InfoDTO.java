package ru.home.itinfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class InfoDTO {
    @Schema(description = "id")
    @JsonProperty("id")
    protected Long id;
    @Schema(description = "Название")
    @JsonProperty("title")
    protected String title;
    @Schema(description = "Издатель")
    @JsonProperty("publisher")
    protected PublisherDTO publisher;
    @Schema(description = "Оценка")
    @JsonProperty("rate")
    protected RateDTO rate;
    @Schema(description = "Состояние")
    @JsonProperty("state")
    protected StateDTO state;
    @Schema(description = "Тэги")
    @JsonProperty("tags")
    protected Set<TagDTO> tags;
    @Schema(description = "Год издания", minimum = "0")
    @JsonProperty("year")
    protected int year;
    @Schema(description = "Описание")
    @JsonProperty("descripts")
    protected List<DescriptDTO> descripts;
    @Schema(description = "Файл или папка")
    @JsonProperty("file")
    protected InfoFileDTO file;
}
