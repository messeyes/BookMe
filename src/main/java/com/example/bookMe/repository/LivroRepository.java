package com.example.bookMe.repository;

import com.example.bookMe.repository.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {

    Optional<LivroEntity> findByIsbn(Long isbn);
}
