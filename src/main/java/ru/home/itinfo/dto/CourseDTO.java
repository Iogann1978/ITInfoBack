package ru.home.itinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Duration;

@Data
@NoArgsConstructor
@SuperBuilder
@Schema(description = "Курсы")
public class CourseDTO extends InfoDTO {
    @Schema(description = "Продолжительность курса")
    private Duration duration;
}
