package ru.home.itinfo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id", "name"})
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private Set<Info> infos;
}
