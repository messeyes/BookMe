package com.example.bookMe.repository;

import com.example.bookMe.repository.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
