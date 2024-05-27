package com.aluracursos.foro_hub.domain.topic;

import com.aluracursos.foro_hub.domain.author.AuthorResponseData;
import com.aluracursos.foro_hub.domain.course.CourseResponseData;

import java.time.LocalDateTime;

public record TopicResponseData(
        Long id,
        String title,
        LocalDateTime created,
        Status status,
        AuthorResponseData author,
        CourseResponseData course
) {
    public TopicResponseData(Topic topic) {
        this(topic.getId(),topic.getTitle(),topic.getCreated(),topic.getStatus(),
                new AuthorResponseData( topic.getAuthor() ),
                new CourseResponseData( topic.getCourse() ));
    }
}
