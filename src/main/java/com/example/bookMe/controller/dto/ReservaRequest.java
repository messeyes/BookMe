package com.example.bookMe.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ReservaRequest {

    @NotNull(message = "O id do usuário é obrigatório!")
    private Long idUsuario;

    @NotNull(message = "O id do livro é obrigatório!")
    private Long idLivro;
}
