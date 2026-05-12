package com.example.bookMe.controller.dto;

import com.example.bookMe.domain.CargoEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
public class UsuarioRequest {
    @NotBlank
    String nome;

    @NotBlank
    String email;

    @NotBlank(message = "Senha é obrigatória!")
    @Min(value = 1000, message = "A senha deve ter no mínimo 4 caracteres e conter apenas números!")
    String senha;

    @NotNull(message = "Cargo é obrigatório!")
    CargoEnum cargo;
}
