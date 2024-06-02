package com.aluracursos.foro_hub.domain.comment.validations;

import com.aluracursos.foro_hub.domain.author.Author;
import com.aluracursos.foro_hub.domain.comment.Comment;
import com.aluracursos.foro_hub.domain.topic.Status;
import com.aluracursos.foro_hub.infra.Validator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CommentSolutionValidaton implements Validator<Comment> {
    @Override
    public Comment validate(Long id, Author user) {
        var comment = user.getTopics().stream().map(t -> t.getComments().stream().filter(c->
            c.getId().equals(id) && t.getStatus().equals(Status.ACTIVO)
            ).toList().get(0)
        ).findFirst();
        if( comment.isEmpty() ) throw new EntityNotFoundException("No existe dicho comentario");
        return comment.get();
    }
}
