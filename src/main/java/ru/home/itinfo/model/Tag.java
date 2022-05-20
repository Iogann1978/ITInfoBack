package ru.home.itinfo.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"tag"})
@Entity
public class Tag {
    @Id
    private String tag;
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<Info> infos;

    public void addInfo(Info info) {
        if (infos == null) {
            infos = new HashSet<>();
        }
        infos.add(info);
    }
}
