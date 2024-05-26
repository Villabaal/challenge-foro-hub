package com.aluracursos.foro_hub.domain.profile;

import com.aluracursos.foro_hub.domain.author.Author;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "profiles")
@Entity(name = "profile")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    private Author author;

    public Profile(Author author, ProfileCreationData data ) {
        this.author = author;
        name = data.name();
    }
}
