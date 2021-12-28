package ru.home.itinfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Описание")
public class DescriptDTO {
    @Schema(description = "id")
    @JsonProperty("id")
    private Long id;
    @Schema(description = "Байты")
    @JsonProperty("text")
    private byte[] text;
}
