package com.aluracursos.foro_hub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class, NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorValidationData> handleNotfound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ErrorValidationData>> handleBadRequest(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(ErrorValidationData::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

}
