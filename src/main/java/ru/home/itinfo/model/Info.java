package ru.home.itinfo.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @EqualsAndHashCode.Exclude
    protected String title;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PUBLISHER_ID")
    protected Publisher publisher;
    @EqualsAndHashCode.Exclude
    protected Rate rate;
    @EqualsAndHashCode.Exclude
    protected State state;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "INFO_TAG", joinColumns = {@JoinColumn(name = "INFO_ID")}, inverseJoinColumns = {@JoinColumn(name = "TAG")})
    protected Set<Tag> tags;
    @EqualsAndHashCode.Exclude
    protected int year;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected Set<Descript> descripts;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(mappedBy = "info", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected InfoFile file;
}
