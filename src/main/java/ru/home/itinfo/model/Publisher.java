package ru.home.itinfo.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
public class Publisher {
    private Long id;
    private String name;
    private Set<Book> books;
}
