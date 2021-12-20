package ru.home.itinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@Schema(description = "Описание")
public class DescriptDTO {
    @Schema(description = "id")
    private Long id;
    @EqualsAndHashCode.Exclude
    @Schema(description = "Байты")
    private byte[] text;
}
