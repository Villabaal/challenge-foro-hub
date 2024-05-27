package com.aluracursos.foro_hub.domain.author;

public record AuthorResponseData(
        Long id,
        String name
) {
    public AuthorResponseData(Author author) {
        this(author.getId(), author.getProfiles().get(0).getName() );
    }
}
