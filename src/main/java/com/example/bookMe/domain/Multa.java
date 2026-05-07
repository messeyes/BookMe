package com.example.bookMe.domain;

public record Multa(
        Long id,
        boolean atraso,
        boolean perca,
        boolean danificado
) {
}
