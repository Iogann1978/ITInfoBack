package ru.home.itinfo.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
public class Info {
    private Long id;
    private String title;
    private Publisher publisher;
    private Rate rate;
    private State state;
    private Set<Tag> tags;
    private int year;
    private Descript descript;
    private byte[] contents;
    private InfoFile file;
}
