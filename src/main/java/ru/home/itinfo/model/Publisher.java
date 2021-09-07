package ru.home.itinfo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "publisher")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Info> infos;
}
