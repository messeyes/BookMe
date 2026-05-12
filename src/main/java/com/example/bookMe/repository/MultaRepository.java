package com.example.bookMe.repository;

import com.example.bookMe.repository.entity.MultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MultaRepository extends JpaRepository<MultaEntity, Long> {
    Optional<MultaEntity> findByEmprestimoId(Long emprestimoId);
}
