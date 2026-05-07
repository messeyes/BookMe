package com.example.bookMe.domain;

import java.time.Instant;

public record Emprestimo(
        Long id,
        Long idLivro,
        Long idUsuario,
        Instant dataEmprestimo,
        Instant dataDevolPrevista,
        Instant dataDevolReal
) {
}
