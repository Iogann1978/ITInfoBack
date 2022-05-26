package ru.home.itinfo.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.home.itinfo.listener.InfoEntityListener;

import javax.persistence.*;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "title"})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(InfoEntityListener.class)
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Column(name = "TITLE", nullable = false)
    protected String title;
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "PUBLISHER_ID", nullable = false)
    protected Publisher publisher;
    @Column(name = "RATE", nullable = false)
    protected Rate rate;
    @Column(name = "STATE", nullable = false)
    protected State state;
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "INFO_TAG", joinColumns = {@JoinColumn(name = "INFO_ID")}, inverseJoinColumns = {@JoinColumn(name = "TAG")})
    protected Set<Tag> tags;
    protected int year;
    @ToString.Exclude
    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    protected Set<Descript> descripts;
    @ToString.Exclude
    @OneToOne(mappedBy = "info", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected InfoFile file;
}
