package ru.home.itinfo.dto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@Schema(description = "Автор")
public class AuthorDTO {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "Имя автора")
    private String name;
}
