package com.aluracursos.foro_hub.domain.course;

public enum Category {
    RANDOM,
    ADVICE,
    TRAVEL,
    FITNESS,
    MUSIC,
    FASHION,
    VIDEOGAMES;

    static public Category fromString(String str){
        return Category.valueOf( str.toUpperCase().trim() );
    }

}
