package com.aluracursos.foro_hub.domain.course;

import com.aluracursos.foro_hub.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "courses")
@Entity(name = "course")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)  private String name;

    @Enumerated(EnumType.STRING) private Category category;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Topic> topics = new ArrayList<>();

    public Course( CourseCreationData data ) {
        this.name = data.name();
        this.category = Category.fromString( data.category() );
    }

    public void addTopics(Topic topic){ topics.add(topic); }

}
