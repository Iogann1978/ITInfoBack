package ru.home.itinfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@Schema(description = "Тэг")
public class TagDTO {
    @Schema(description = "id")
    @JsonProperty("id")
    private Long id;
    @Schema(description = "Наименование")
    @JsonProperty("tag")
    private String tag;
}
