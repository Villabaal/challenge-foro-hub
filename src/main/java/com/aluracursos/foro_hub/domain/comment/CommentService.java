package com.aluracursos.foro_hub.domain.comment;

import com.aluracursos.foro_hub.domain.author.Author;
import com.aluracursos.foro_hub.domain.author.AuthorRepository;
import com.aluracursos.foro_hub.domain.comment.validations.CommentAuthorValidaton;
import com.aluracursos.foro_hub.domain.comment.validations.CommentSolutionValidaton;
import com.aluracursos.foro_hub.domain.topic.*;
import com.aluracursos.foro_hub.infra.security.TokenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CommentService {
    @Autowired private AuthorRepository userRepo;
    @Autowired private CommentRepository commentRepo;
    @Autowired private TopicRepository topictRepo;

    @Autowired TokenService tokenService;
    @Autowired CommentAuthorValidaton putValidator;
    @Autowired CommentSolutionValidaton toggleValidator;

    public CommentResponseData commentCreation(CommentCreationData data, String token ) {
        var email = tokenService.getSubject(token);
        var user = (Author) userRepo.findByEmail(email).orElseThrow();
        var topic = topictRepo.findIfActive(data.topic()).orElseThrow();
        var comment = new Comment( data.message(), user, topic );
        user.addComment(comment);
        topic.addComment(comment);
        return new CommentResponseData(comment);
    }

    public Page<?> commentList(String topicTitle,Pageable pag){
        var topic = topictRepo.findByTitleContainingIgnoreCase(topicTitle).orElseThrow();
        return new  PageImpl<>(topic.getComments().stream().map(CommentResponseListData::new).toList());
    }

    public CommentResponseData commentDetail( Long id ){
        var comment = commentRepo.findById(id);
        if(comment.isEmpty()) throw new EntityNotFoundException(" No existe el comentario ");
        return new CommentResponseData( comment.get() );
    }

    public CommentResponseData commentUpdate(CommentUpdateData data, String token){
        var email = tokenService.getSubject(token);
        var user = (Author) userRepo.findByEmail(email).orElseThrow();
        var comment = putValidator.validate(data.id(),user);
        comment.update(data);
        return new CommentResponseData( comment );
    }
    public void commentErase(Long id, String token){
        var email = tokenService.getSubject(token);
        var user = (Author) userRepo.findByEmail(email).orElseThrow();
        var comment = commentRepo.findById(id);
        if( comment.isEmpty() ) throw new EntityNotFoundException("No existe dicho comentario");
        var cmmt = comment.get();
        if(!cmmt.getTopic().getAuthor().equals(user) && !cmmt.getAuthor().equals(user))
            throw new EntityNotFoundException("usuario no autorizado");
        commentRepo.delete( cmmt );
    }

    public CommentResponseData toggleSolutionFlag(Long id, String token) {
        var email = tokenService.getSubject(token);
        var user = (Author) userRepo.findByEmail(email).orElseThrow();
        var comment = toggleValidator.validate(id,user);
        comment.setSolution( !comment.getSolution() );
        return new CommentResponseData( comment );
    }
}
