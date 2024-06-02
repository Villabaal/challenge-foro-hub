package com.aluracursos.foro_hub.controller;

import com.aluracursos.foro_hub.domain.comment.CommentCreationData;
import com.aluracursos.foro_hub.domain.comment.CommentResponseData;
import com.aluracursos.foro_hub.domain.comment.CommentService;
import com.aluracursos.foro_hub.domain.comment.CommentUpdateData;
import com.aluracursos.foro_hub.domain.topic.TopicCreationData;
import com.aluracursos.foro_hub.domain.topic.TopicResponseData;
import com.aluracursos.foro_hub.domain.topic.TopicUpdateData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/comentarios")
public class CommentController {
    @Autowired CommentService service;

    @PostMapping
    @Transactional
    //@Operation(summary = "Registra un nuevo medico en la base de datos")
    public ResponseEntity<CommentResponseData> commentCreation(@RequestBody @Valid CommentCreationData data,
                                                             @RequestHeader("Authorization") String auth , UriComponentsBuilder uriCompBldr) {
        var token = getToken(auth);
        var responseData = service.commentCreation(data,token);
        var url = uriCompBldr.path("/comentarios/{id}").buildAndExpand( responseData.id() ).toUri();
        return ResponseEntity.created(url).body(responseData);
    }

    @GetMapping
    //@Operation(summary = "Obtiene el listado de medicos")
    public ResponseEntity<Page<?>> commentList(@RequestParam("titulo") String topicTitle,@PageableDefault(size = 2) Pageable pag) {
        return ResponseEntity.ok( service.commentList(topicTitle,pag) );
    }

    @GetMapping("/{id}")
    //@Operation(summary = "Obtiene los registros del medico con ID")
    public ResponseEntity<CommentResponseData> commentDetail(@PathVariable Long id) {
        var topicData = service.commentDetail(id);
        return ResponseEntity.ok(topicData);
    }

    @PutMapping
    @Transactional
    //@Operation(summary = "Actualiza los datos de un medico existente")
    public ResponseEntity<CommentResponseData> commentUpdate(@RequestBody @Valid CommentUpdateData data, @RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok( service.commentUpdate( data , getToken(auth) ) );
    }

    @DeleteMapping("/{id}")
    @Transactional
    //@Operation(summary = "Elimina un medico registrado - inactivo")
    public ResponseEntity<Void> commentErase(@PathVariable Long id, @RequestHeader("Authorization") String auth) {
        System.out.println("si entro");
        service.commentErase( id , getToken(auth) );
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CommentResponseData> solutionFlagToggle(@PathVariable Long id,@RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok( service.toggleSolutionFlag( id, getToken(auth) ) );
    }

    private String getToken(String auth){ return auth.replace("Bearer ", ""); }
}
