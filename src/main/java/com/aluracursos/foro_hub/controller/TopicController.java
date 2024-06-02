package com.aluracursos.foro_hub.controller;


import com.aluracursos.foro_hub.domain.topic.*;
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
@RequestMapping("/topicos")
public class TopicController {

    @Autowired TopicService service;

    @PostMapping
    @Transactional
    //@Operation(summary = "Registra un nuevo medico en la base de datos")
    public ResponseEntity<TopicResponseData> topicCreation(@RequestBody @Valid TopicCreationData data,
                                                             @RequestHeader("Authorization") String auth , UriComponentsBuilder uriCompBldr) {
        var token = getToken(auth);
        var responseData = service.topicCreation(data,token);
        var url = uriCompBldr.path("/topicos/{id}").buildAndExpand( responseData.id() ).toUri();
        return ResponseEntity.created(url).body(responseData);
    }

    @GetMapping
    //@Operation(summary = "Obtiene el listado de medicos")
    public ResponseEntity<Page<TopicResponseData>> topicList(@PageableDefault(size = 2) Pageable pag) {
        var topicsData = service.topicList(pag);
        return ResponseEntity.ok( topicsData );
    }

    @GetMapping("/{id}")
    //@Operation(summary = "Obtiene los registros del medico con ID")
    public ResponseEntity<TopicResponseData> topicDetail(@PathVariable Long id) {
        var topicData = service.topicDetail(id);
        return ResponseEntity.ok(topicData);
    }

    @PutMapping
    @Transactional
    //@Operation(summary = "Actualiza los datos de un medico existente")
    public ResponseEntity<TopicResponseData> topicUpdate(@RequestBody @Valid TopicUpdateData data, @RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok( service.topicUpdate(data , getToken(auth) ) );
    }

    @DeleteMapping("/{id}")
    @Transactional
    //@Operation(summary = "Elimina un medico registrado - inactivo")
    public ResponseEntity<Void> topicDelete(@PathVariable Long id, @RequestHeader("Authorization") String auth) {
        service.topicDeactivate( id , getToken(auth) );
        return ResponseEntity.noContent().build();
    }

    private String getToken(String auth){ return auth.replace("Bearer ", ""); }

}
