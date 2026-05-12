package com.example.bookMe.domain;

import com.example.bookMe.repository.entity.EmprestimoEntity;

import java.math.BigDecimal;

public record Multa(
        Long id,
        EmprestimoEntity emprestimo,
        BigDecimal valor,
        boolean pago,
        String tipo
) {
}
