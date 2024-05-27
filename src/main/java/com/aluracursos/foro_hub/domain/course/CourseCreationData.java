package com.aluracursos.foro_hub.domain.course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CourseCreationData(
        @NotBlank
        @Size(min = 0, max = 50)
        String name,

        @NotBlank
        @Size(min = 0, max = 50)
        String category
) {
}
