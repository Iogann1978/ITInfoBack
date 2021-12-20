package ru.home.itinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
public class InfoDTO {
    @Schema(description = "id")
    protected Long id;
    @Schema(description = "Название")
    protected String title;
    @Schema(description = "Издатель")
    protected PublisherDTO publisher;
    @Schema(description = "Оценка")
    protected RateDTO rate;
    @Schema(description = "Состояние")
    protected StateDTO state;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Schema(description = "Тэги")
    protected Set<TagDTO> tags;
    @Schema(description = "Год издания")
    protected int year;
    @Schema(description = "Описание")
    protected DescriptDTO descript;
    @Schema(description = "Содержание")
    protected ContentDTO contentDTO;
    @Schema(description = "Файл или папка")
    protected InfoFileDTO file;
}
