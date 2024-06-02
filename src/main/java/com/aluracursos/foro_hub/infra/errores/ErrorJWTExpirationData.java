package com.aluracursos.foro_hub.infra.errores;

import com.auth0.jwt.exceptions.TokenExpiredException;

public record ErrorJWTExpirationData(
        String expired_on
) {
    public ErrorJWTExpirationData(TokenExpiredException e) {
        this( e.getExpiredOn().toString() );
    }
}
