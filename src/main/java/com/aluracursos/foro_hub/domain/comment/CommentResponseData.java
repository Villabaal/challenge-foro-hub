package com.aluracursos.foro_hub.domain.comment;

import com.aluracursos.foro_hub.domain.author.AuthorResponseData;
import com.aluracursos.foro_hub.domain.topic.TopicResponseData;


import java.time.LocalDateTime;

public record CommentResponseData(
        Long id,
        String message,
        LocalDateTime created,
        Boolean solution,
        AuthorResponseData author,
        TopicResponseData topic
) {
    CommentResponseData(Comment comment){
        this(comment.getId(), comment.getMessage(), comment.getCreated(),comment.getSolution(),
                new AuthorResponseData(comment.getAuthor()),new TopicResponseData(comment.getTopic()));
    }
}
