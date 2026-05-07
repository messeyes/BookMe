package com.example.bookMe.controller;

import com.example.bookMe.controller.dto.UsuarioRequest;
import com.example.bookMe.domain.Usuario;
import com.example.bookMe.repository.entity.UsuarioEntity;
import com.example.bookMe.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> criar(@RequestBody @Valid UsuarioRequest usuarioRequest){
        UsuarioEntity criado = usuarioService.criar(new Usuario(null, usuarioRequest.getNome(), usuarioRequest.getEmail(), usuarioRequest.getSenha(), null, Instant.now()));
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> listar(){
        return ResponseEntity.ok(usuarioService.listarTodosUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
