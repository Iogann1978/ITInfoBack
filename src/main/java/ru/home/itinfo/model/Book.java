package ru.home.itinfo.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
public class Book extends Info {
    private int pages;
    private String isbn;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "BOOK_AUTHOR", joinColumns = {@JoinColumn(name = "BOOK_ID")}, inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID")})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Author> authors;
}
