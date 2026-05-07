package com.example.bookMe.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UsuarioRequest {
    @NotBlank
    String nome;

    @NotBlank
    String email;

    @Size(min = 4, message = "A senha deve ter no mínimo 4 caracteres!")
    int senha;

    Long idCargo;

    Instant dataCadastro;
}
