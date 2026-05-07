package com.example.bookMe.controller.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LivroRequest {
    @NotBlank
    String titulo;

    @NotBlank
    String autor;

    @NotNull
    @Min(value = 1, message = "O ISBN não pode ser negativo!")
    Long isbn;

    @Min(value = 1, message = "Deve conter pelo menos um livro!")
    int quantTotal;
}
