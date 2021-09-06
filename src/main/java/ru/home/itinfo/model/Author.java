package ru.home.itinfo.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
public class Author {
    private Long id;
    private String name;
    private String normalizedName;
    private Set<Book> books;
}
