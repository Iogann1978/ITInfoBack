package ru.home.itinfo.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
public class Tag {
    private Long id;
    private String tag;
    private Set<Book> books;
}
