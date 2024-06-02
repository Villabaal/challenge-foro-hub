package com.aluracursos.foro_hub.domain.topic.validations;

import com.aluracursos.foro_hub.domain.author.Author;
import com.aluracursos.foro_hub.domain.topic.Status;
import com.aluracursos.foro_hub.domain.topic.Topic;
import com.aluracursos.foro_hub.infra.Validator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class TopicAuthorValidaton implements Validator<Topic> {

    @Override
    public Topic validate(Long id, Author user) {
        var topic = user.getTopics().stream().filter( t ->
                t.getId().equals(id) && t.getStatus().equals(Status.ACTIVO)
        ).findFirst();
        if( topic.isEmpty() ) throw new EntityNotFoundException("No existe dicho topico");
        return topic.get();
    }

}
