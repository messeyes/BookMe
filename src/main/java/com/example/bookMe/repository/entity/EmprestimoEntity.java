package com.example.bookMe.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class EmprestimoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idLivro", nullable = false)
    private LivroEntity livro;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private UsuarioEntity usuario;

    @NonNull
    @Column(nullable = false)
    private Instant dataEmprestimo;

    @NonNull
    @Column(nullable = false)
    private LocalDate dataDevolPrevista;


    @Column
    private Instant dataDevolReal;

    public EmprestimoEntity(){}

    public EmprestimoEntity(UsuarioEntity usuario, LivroEntity livro){
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = Instant.now();
        this.dataDevolPrevista = LocalDate.now().plusWeeks(2);
    }
}
