package com.example.bookMe.repository;

import com.example.bookMe.repository.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
    int countByLivroId(Long idLivro);
}
