package com.example.bookMe.domain;

import java.time.Instant;

public record Usuario(
        Long id,
        String nome,
        String email,
        String senha,
        CargoEnum cargo,
        Instant dataCadastro
) {}
