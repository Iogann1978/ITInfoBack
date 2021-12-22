package ru.home.itinfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@Schema(description = "Содержание")
public class ContentDTO {
    @Schema(description = "id")
    @JsonProperty("id")
    private Long id;
    @Schema(description = "Байты")
    @JsonProperty("text")
    private byte[] text;
}
