package ru.home.itinfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Тэг")
public class TagDTO {
    @Schema(description = "Наименование")
    @JsonProperty("tag")
    private String tag;
    @Schema(description = "Количество книг или курсов по тэгу")
    @JsonProperty("count")
    private Integer count;
}
