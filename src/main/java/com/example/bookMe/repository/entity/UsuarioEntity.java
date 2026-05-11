package com.example.bookMe.repository.entity;

import com.example.bookMe.domain.CargoEnum;
import com.example.bookMe.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@JsonPropertyOrder({"id", "nome", "email", "senha", "idCargo", "dataCadastro"})
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String nome;

    @NonNull
    @Column(nullable = false)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String senha;

    @NonNull
    @Column(nullable = false)
    private Instant dataCadastro;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CargoEnum cargo;

    public UsuarioEntity(){}

    public UsuarioEntity(Usuario usuario){
        this.nome = usuario.nome();
        this.email = usuario.email();
        this.senha = usuario.senha();
        this.dataCadastro = usuario.dataCadastro();
        this.cargo = usuario.cargo();
    }

}
