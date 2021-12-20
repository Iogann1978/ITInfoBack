package ru.home.itinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@Schema(description = "Тэг")
public class TagDTO {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "Наименование")
    private String tag;
}
