package ru.home.itinfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@NoArgsConstructor
@SuperBuilder
@Schema(description = "Книга")
public class BookDTO extends InfoDTO {
    @Schema(description = "Количество страниц", minimum = "1")
    @JsonProperty("pages")
    private int pages;
    @Schema(description = "ISBN")
    @JsonProperty("isbn")
    private String isbn;
    @Schema(description = "Авторы книги")
    @JsonProperty("authors")
    private Set<AuthorDTO> authors;
}
