package com.aluracursos.foro_hub.domain.author;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorAuthData(
        @NotBlank
        @Email
        @Size(min = 0, max = 100)
        String email,

        @NotBlank
        @Size(min = 0, max = 20)
        String password
) {
}
