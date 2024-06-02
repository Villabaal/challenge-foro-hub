package com.aluracursos.foro_hub.infra;


import com.aluracursos.foro_hub.domain.author.Author;


public interface Validator<OwnedEntity>  {
    OwnedEntity validate(Long id, Author user);
}
