package com.aluracursos.foro_hub.domain.comment;

import com.aluracursos.foro_hub.domain.author.Author;
import com.aluracursos.foro_hub.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "comments")
@Entity(name = "comment")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime created;
    private Boolean solution;
    @ManyToOne private Author author;
    @ManyToOne private Topic topic;

    public Comment(String message, Author author, Topic topic) {
        this.message = message;
        this.author = author;
        this.topic = topic;
        created = LocalDateTime.now();
    }

    public void setSolution(Boolean solution) {
        this.solution = solution;
    }
}
