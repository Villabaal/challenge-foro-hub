package com.aluracursos.foro_hub.domain.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentUpdateData(
        @NotNull
        Long id,

        @Size(min = 0, max = 255)
        @NotBlank
        String message
) {
}
