package com.example.bookMe.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MultaRequest {
    @NotNull(message = "O id do empréstimo é obrigatório!")
    private  Long idEmprestimo;

    @NotNull(message = "O valor é obrigatório!")
    private BigDecimal valor;

    @NotNull(message = "O tipo é obrigatório!")
    private String tipo;
}
