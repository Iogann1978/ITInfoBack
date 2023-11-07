package ru.home.itinfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Книга")
public class FindDTO {
    @Schema(description = "Название")
    @JsonProperty("title")
    private String title;
    @Schema(description = "Тип")
    @JsonProperty("type")
    private FindType type;

    public enum FindType {
        BOOK, COURSE;
    }
}
