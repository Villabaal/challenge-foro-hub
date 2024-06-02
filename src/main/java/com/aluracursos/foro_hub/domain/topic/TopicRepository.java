package com.aluracursos.foro_hub.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findByStatusTrue(Pageable pag);

    @Query(value = """
            select * from topics
            where status=1 and id=:id limit 1
            """,nativeQuery = true)
    Optional<Topic> findIfActive(Long id);

    Optional<Topic> findByTitleContainingIgnoreCase(String title);
}
