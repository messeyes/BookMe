package com.example.bookMe.repository.entity;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "id_livro",nullable = false)
    private LivroEntity livro;

    @Column(nullable = false)
    private Instant dataReserva;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private int ordemFila;

    public ReservaEntity(){}

    public ReservaEntity(UsuarioEntity usuario, LivroEntity livro, int ordemFila){
        this.usuario = usuario;
        this.livro = livro;
        this.dataReserva = Instant.now();
        this.status = "PENDENTE";
        this.ordemFila = ordemFila;
    }
}
