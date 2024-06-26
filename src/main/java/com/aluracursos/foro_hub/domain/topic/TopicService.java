package com.aluracursos.foro_hub.domain.topic;

import com.aluracursos.foro_hub.domain.author.Author;
import com.aluracursos.foro_hub.domain.author.AuthorRepository;
import com.aluracursos.foro_hub.domain.course.CourseRepository;
import com.aluracursos.foro_hub.infra.security.TokenService;

import com.aluracursos.foro_hub.domain.topic.validations.TopicAuthorValidaton;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private AuthorRepository userRepo;
    @Autowired private CourseRepository courseRepo;
    @Autowired private TopicRepository topicRepo;
    @Autowired TokenService tokenService;
    @Autowired TopicAuthorValidaton userValidator;


    public TopicResponseData topicCreation( TopicCreationData data, String token ) {
        var email = tokenService.getSubject(token);
        var user = (Author) userRepo.findByEmail(email).orElseThrow();
        var course = courseRepo.findByName(data.course()).orElseThrow();
        var topic = new Topic(data, user, course);
        course.addTopics(topic);
        user.addTopics(topic);
        return new TopicResponseData(topic);
    }

    public Page<TopicResponseData> topicList(Pageable pag){
        return topicRepo.findByStatusTrue(pag).map(TopicResponseData::new);
    }

    public TopicResponseData topicDetail( Long id ){
        var topic = topicRepo.findIfActive(id);
        if(topic.isEmpty()) throw new EntityNotFoundException(" Inactivo ");
        return new TopicResponseData( topic.get() );
    }

    public TopicResponseData topicUpdate(TopicUpdateData data, String token){
        var email = tokenService.getSubject(token);
        var user = (Author) userRepo.findByEmail(email).orElseThrow();
        var topic = userValidator.validate( data.id(), user );
        topic.update(data);
        return new TopicResponseData( topic );
    }

    public void topicDeactivate(Long id, String token){
        var email = tokenService.getSubject(token);
        var user = (Author) userRepo.findByEmail(email).orElseThrow();
        var topic = userValidator.validate( id, user );
        topic.setStatus( Status.CERRADO );
    }
}
