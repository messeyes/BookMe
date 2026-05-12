package com.example.bookMe.domain;

import java.time.Instant;

public record Reserva(
        Long id,
        Long idUsuario,
        Long idLivro,
        Instant dataReserva,
        String status,
        int ordemFila
) {
}
