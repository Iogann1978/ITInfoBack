package ru.home.itinfo.dto;

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
    private int pages;
    @Schema(description = "ISBN")
    private String isbn;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Schema(description = "Авторы книги")
    private Set<AuthorDTO> authors;
}
