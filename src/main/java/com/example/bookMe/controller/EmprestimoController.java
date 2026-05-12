package com.example.bookMe.controller;

import com.example.bookMe.controller.dto.EmprestimoRequest;
import com.example.bookMe.repository.entity.EmprestimoEntity;
import com.example.bookMe.service.EmprestimoService;
import com.example.bookMe.service.LivroService;
import com.example.bookMe.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    private final EmprestimoService emprestimoService;
    private final UsuarioService usuarioService;
    private final LivroService livroService;

    public EmprestimoController(EmprestimoService emprestimoService, UsuarioService usuarioService, LivroService livroService){
        this.emprestimoService = emprestimoService;
        this.usuarioService = usuarioService;
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<EmprestimoEntity> criar(@RequestBody @Valid EmprestimoRequest request){
        return ResponseEntity.status(201).body(emprestimoService.criar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoEntity> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(emprestimoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoEntity>> listarTodos(){
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }

    @PatchMapping("/{id}/devolver")
    public ResponseEntity<EmprestimoEntity> devolver(@PathVariable Long id){
        return ResponseEntity.ok(emprestimoService.devolver(id));
    }
}
