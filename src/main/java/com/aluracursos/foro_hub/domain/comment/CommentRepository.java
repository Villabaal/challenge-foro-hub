package com.aluracursos.foro_hub.domain.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface CommentRepository extends JpaRepository<Comment, Long> {
}
