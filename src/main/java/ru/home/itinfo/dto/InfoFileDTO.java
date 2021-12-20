package ru.home.itinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@Schema(description = "Файл или папка")
public class InfoFileDTO {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "Имя файла или папки")
    private String filename;
    @Schema(description = "Размер")
    private Long size;
}
