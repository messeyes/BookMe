package com.example.bookMe.repository.entity;

import com.example.bookMe.domain.Livro;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Long id;

    @NonNull
    @Column (nullable = false)
    private String titulo;

    @NonNull
    @Column (nullable = false)
    private String autor;

    @NonNull
    @Column (nullable = false, unique = true)
    private Long isbn;

    @Column
    private int quantTotal;

    @Column
    private int quantDisponivel;

    public LivroEntity() {}

    public LivroEntity(Livro livro) {
        this.titulo = livro.titulo();
        this.autor = livro.autor();
        this.isbn = livro.isbn();
        this.quantTotal = livro.quantTotal();
        this.quantDisponivel = livro.quantDisponivel();
    }

}
