package com.example.bookMe.repository.entity;

import com.example.bookMe.domain.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
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
    private int senha;

    @NonNull
    @Column(nullable = false)
    private Instant dataCadastro;

    @NonNull
    @Column(nullable = false)
    private Long idCargo;

    public UsuarioEntity(){}

    public UsuarioEntity(Usuario usuario){
        this.nome = usuario.nome();
        this.email = usuario.email();
        this.senha = usuario.senha();
        this.dataCadastro = usuario.dataCadastro();
        this.idCargo = usuario.idCargo();
    }

}
