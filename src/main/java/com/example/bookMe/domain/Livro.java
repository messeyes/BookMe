package com.example.bookMe.domain;


public record Livro(
        Long id,
        String titulo,
        String autor,
        Long isbn,
        int quantTotal,
        int quantDisponivel
) {}
