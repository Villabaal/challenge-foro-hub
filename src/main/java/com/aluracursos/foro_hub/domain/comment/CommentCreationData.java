package com.aluracursos.foro_hub.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CommentCreationData(
        @NotNull
        Long topic,

        @NotBlank
        @Size(min = 0, max = 255)
        String message
) {
}
