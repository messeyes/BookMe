package com.example.bookMe.domain;

import java.time.Instant;

public record Usuario(
        Long id,
        String nome,
        String email,
        int senha,
        Long idCargo,
        Instant dataCadastro
) {}
