package com.aluracursos.foro_hub.controller;

import com.aluracursos.foro_hub.domain.author.Author;
import com.aluracursos.foro_hub.domain.author.AuthorRepository;
import com.aluracursos.foro_hub.domain.author.AuthorResponseData;
import com.aluracursos.foro_hub.domain.course.Course;
import com.aluracursos.foro_hub.domain.course.CourseCreationData;
import com.aluracursos.foro_hub.domain.course.CourseRepository;
import com.aluracursos.foro_hub.domain.course.CourseResponseData;
import com.aluracursos.foro_hub.domain.topic.Topic;
import com.aluracursos.foro_hub.domain.topic.TopicRepository;
import com.aluracursos.foro_hub.domain.topic.TopicResponseData;
import com.aluracursos.foro_hub.domain.topic.TopicCreationData;
import com.aluracursos.foro_hub.infra.security.TokenService;
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
@RequestMapping("/cursos")
public class CourseController {
    @Autowired private CourseRepository courseRepo;
    @Autowired TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity<CourseResponseData> courseRegister(@RequestBody @Valid CourseCreationData data, UriComponentsBuilder uriCompBldr) {
        var course = courseRepo.save( new Course(data) );
        var responseData = new CourseResponseData( course );
        var url = uriCompBldr.path("/cursos/{id}").buildAndExpand( course.getId() ).toUri();
        return ResponseEntity.created(url).body( responseData );
    }

    //TODO - petición de listado y detalles de cursos

}
