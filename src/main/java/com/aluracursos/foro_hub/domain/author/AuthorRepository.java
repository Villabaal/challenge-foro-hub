package com.aluracursos.foro_hub.domain.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<UserDetails> findByEmail(String email);
}
