package com.aluracursos.foro_hub.domain.topic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TopicUpdateData(
        @NotNull
        Long id,

        @Size(min = 0, max = 60)
        @NotBlank
        String title,

        @Size(min = 0, max = 255)
        @NotBlank
        String message
) {
}
