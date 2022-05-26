package ru.home.itinfo.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.home.itinfo.listener.BookEntityListener;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(BookEntityListener.class)
public class Book extends Info {
    private int pages;
    private String isbn;
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "BOOK_AUTHOR", joinColumns = {@JoinColumn(name = "BOOK_ID")}, inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID")})
    private Set<Author> authors;
}
