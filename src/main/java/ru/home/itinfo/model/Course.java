package ru.home.itinfo.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
public class Course extends Info {
    private Integer duration;
}
