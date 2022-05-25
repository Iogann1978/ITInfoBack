package ru.home.itinfo.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
public class Book extends Info {
    private int pages;
    private String isbn;
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "BOOK_AUTHOR", joinColumns = {@JoinColumn(name = "BOOK_ID")}, inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID")})
    private Set<Author> authors;

    @Override
    protected void prepersist_child() {
        if (!CollectionUtils.isEmpty(authors)) {
            for (Author a : authors) {
                a.addBook(this);
            }
        }
    }
}
