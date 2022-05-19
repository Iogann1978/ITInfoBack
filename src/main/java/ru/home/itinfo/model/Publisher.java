package ru.home.itinfo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @EqualsAndHashCode.Exclude
    @Column(name = "NAME", nullable = false)
    private String name;
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private Set<Info> infos;
}
