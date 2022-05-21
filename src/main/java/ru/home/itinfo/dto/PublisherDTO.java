package ru.home.itinfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Издатель")
public class PublisherDTO {
    @Schema(description = "id")
    @JsonProperty("id")
    private Long id;
    @Schema(description = "Наименование")
    @JsonProperty("name")
    private String name;
    @Schema(description = "Количество книг или курсов издателя")
    @JsonProperty("infoCount")
    private Integer count;
}
