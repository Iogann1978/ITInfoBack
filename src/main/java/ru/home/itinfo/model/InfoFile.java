package ru.home.itinfo.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id", "filename"})
@Entity
public class InfoFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "FILENAME", nullable = false)
    private String filename;
    private Long size;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INFO_ID")
    private Info info;
}
