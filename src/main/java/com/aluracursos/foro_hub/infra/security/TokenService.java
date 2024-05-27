package com.aluracursos.foro_hub.infra.security;

import com.aluracursos.foro_hub.domain.author.Author;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class TokenService {

    @Value("${api.security.secret}") private String apiSecret;

    public String tokenGen(Author user){
        Algorithm algorithm = Algorithm.HMAC256( apiSecret );
        return JWT.create().withIssuer("foro hub")
                .withSubject( user.getUsername() )
                .withClaim("id",user.getId() )
                .withExpiresAt( generateExpDate() )
                .sign(algorithm);
    }

    public String getSubject( String token ){
        var algorithm = Algorithm.HMAC256( apiSecret );
        var verifier = JWT.require(algorithm)
                .withIssuer("foro hub")
                .build()
                .verify(token);
        var subject = Optional.ofNullable( verifier.getSubject() );
        if ( subject.isEmpty() ) throw new RuntimeException("Verifier invalido");
        return subject.get();
    }

    private Instant generateExpDate(){
        return LocalDateTime.now().plusHours(2).toInstant( ZoneOffset.ofHours(-6) );
    }
}
