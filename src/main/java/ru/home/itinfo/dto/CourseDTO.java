package ru.home.itinfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("duration")
    private Duration duration;
}
