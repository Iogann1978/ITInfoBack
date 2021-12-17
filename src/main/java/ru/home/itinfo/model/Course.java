package ru.home.itinfo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.time.Duration;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
public class Course extends Info {
    private Duration duration;
}
