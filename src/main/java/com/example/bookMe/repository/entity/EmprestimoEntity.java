package com.example.bookMe.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class EmprestimoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private Long idLivro;

    @NonNull
    @Column(nullable = false)
    private Long idUsuario;

    @NonNull
    @Column(nullable = false)
    private Instant dataEmprestimo;

    @NonNull
    @Column(nullable = false)
    private Instant dataDevolPrevista;

    @NonNull
    @Column(nullable = false)
    private Instant dataDevolReal;
}
