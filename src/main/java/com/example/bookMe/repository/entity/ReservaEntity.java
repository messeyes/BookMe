package com.example.bookMe.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class ReservaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(nullable = false)
    private Long idUsuario;

    @NonNull
    @Column(nullable = false)
    private Long idLivro;

    @NonNull
    @Column(nullable = false)
    private Instant dataReserva;

    @NonNull
    @Column(nullable = false)
    private boolean status;

    @NonNull
    @Column(nullable = false)
    private int ordemFila;
}
