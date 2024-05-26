package com.aluracursos.foro_hub.domain.author;

public record AuthorRegisterData(
        String name,
        String email,
        String password
) {
}
