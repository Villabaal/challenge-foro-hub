package com.aluracursos.foro_hub.domain.topic;

import com.aluracursos.foro_hub.domain.author.Author;
import com.aluracursos.foro_hub.domain.comment.Comment;
import com.aluracursos.foro_hub.domain.course.Course;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topics",
        uniqueConstraints={ @UniqueConstraint(columnNames = {"title", "course"}) ,
                            @UniqueConstraint(columnNames = {"title", "message"})} )
@Entity(name = "topic")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime created;
    @Setter  @Enumerated(EnumType.ORDINAL)  private Status status;
    @ManyToOne private Author author;
    @ManyToOne private Course course;

    @OneToMany(mappedBy = "topic",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    public Topic(TopicCreationData data, Author author, Course course) {
        title = data.title();
        message = data.message();
        this.author = author;
        this.course = course;
        created = LocalDateTime.now();
        status = Status.ACTIVO;
    }

    public void addComment( Comment  comment ) { comments.add(comment); }

    public void update(TopicUpdateData data) {
        if ( data.title() != null) title = data.title();
        if ( data.message() != null) message = data.message();
    }
}
