package com.aluracursos.foro_hub.infra.errores;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.validation.FieldError;

public record ErrorValidationData(String field, String error) {
    public ErrorValidationData(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }

}
