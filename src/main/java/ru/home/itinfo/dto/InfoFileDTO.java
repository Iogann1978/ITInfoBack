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
@Schema(description = "Файл или папка")
public class InfoFileDTO {
    @Schema(description = "id")
    @JsonProperty("id")
    private Long id;
    @Schema(description = "Имя файла или папки")
    @JsonProperty("filename")
    private String filename;
    @Schema(description = "Размер")
    @JsonProperty("size")
    private Long size;
}
