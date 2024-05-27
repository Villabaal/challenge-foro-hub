package com.aluracursos.foro_hub.controller;

import com.aluracursos.foro_hub.domain.author.Author;
import com.aluracursos.foro_hub.domain.author.AuthorAuthData;
import com.aluracursos.foro_hub.infra.security.DatosJWTToken;
import com.aluracursos.foro_hub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entrar")
public class AuthenticationController {
    @Autowired private AuthenticationManager manager;
    @Autowired  private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTToken> authUser(@RequestBody @Valid AuthorAuthData data ){
        var authToken = new UsernamePasswordAuthenticationToken(data.email(),data.password());
        var authUser = manager.authenticate(authToken);
        var token = tokenService.tokenGen( (Author) authUser.getPrincipal() );
        return ResponseEntity.ok( new DatosJWTToken( token ) );
    }
}
