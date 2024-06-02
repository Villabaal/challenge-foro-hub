package com.aluracursos.foro_hub.controller;

import com.aluracursos.foro_hub.domain.author.Author;
import com.aluracursos.foro_hub.domain.author.AuthorRegisterData;
import com.aluracursos.foro_hub.domain.author.AuthorRepository;
import com.aluracursos.foro_hub.domain.author.AuthorResponseData;
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
@RequestMapping("/autores")
//@SecurityRequirement(name = "bearer-key")
public class AuthorController {
    @Autowired private AuthorRepository repo;

    @PostMapping("/registrar")
    @Transactional
    //@Operation(summary = "Registra un nuevo author/user en la base de datos")
    public ResponseEntity<AuthorResponseData> authorRegister(@RequestBody @Valid AuthorRegisterData data,
                                                                UriComponentsBuilder uriCompBldr) {
        var user = repo.save(new Author(data));
        var responseData = new AuthorResponseData( user );
        var url = uriCompBldr.path("/autores/{id}").buildAndExpand( user.getId() ).toUri();
        return ResponseEntity.created(url).body( responseData );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseData> getAuthorData(@PathVariable Long id ){
        var datos = new AuthorResponseData( repo.findById( id ).orElseThrow() );
        return ResponseEntity.ok(datos);
    }

    @GetMapping
    public ResponseEntity<Page<AuthorResponseData>> getAuthorListData(@PageableDefault(size = 2) Pageable pag){
        var datos = repo.findAll(pag).map(AuthorResponseData::new);
        return ResponseEntity.ok( datos );
    }
}
