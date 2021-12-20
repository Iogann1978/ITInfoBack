package ru.home.itinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
@Schema(description = "Издатель")
public class PublisherDTO {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "Наименование")
    private String name;
}
