package com.aluracursos.foro_hub.domain.comment;

import com.aluracursos.foro_hub.domain.author.AuthorResponseData;
import com.aluracursos.foro_hub.domain.topic.TopicResponseData;

import java.time.LocalDateTime;

public record CommentResponseListData(
        Long id,
        String message,
        LocalDateTime created,
        Boolean solution,
        String author
) {
    CommentResponseListData(Comment comment){
        this( comment.getId(), comment.getMessage(), comment.getCreated(),comment.getSolution(), comment.getAuthor().getName() );
    }
}
