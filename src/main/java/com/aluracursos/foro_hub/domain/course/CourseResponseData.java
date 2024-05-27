package com.aluracursos.foro_hub.domain.course;

public record CourseResponseData(String name,Category category) {
    public CourseResponseData(Course course) {
        this(course.getName(),course.getCategory());
    }
}
