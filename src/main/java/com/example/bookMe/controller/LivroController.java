package com.example.bookMe.controller;

import com.example.bookMe.controller.dto.LivroRequest;
import com.example.bookMe.domain.Livro;
import com.example.bookMe.repository.entity.LivroEntity;
import com.example.bookMe.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private LivroService livroService;

    public LivroController( LivroService livroService){
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroEntity> criar(@RequestBody @Valid LivroRequest livroRequest){
        LivroEntity criado = livroService.criar(new Livro(null, livroRequest.getTitulo() , livroRequest.getAutor(), livroRequest.getIsbn(), livroRequest.getQuantTotal(), livroRequest.getQuantTotal()));
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<LivroEntity>> listar(){
        return ResponseEntity.ok(livroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroEntity> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<LivroEntity> buscarPorIsbn(@PathVariable Long isbn){
        return ResponseEntity.ok(livroService.buscarPorIsbn(isbn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroEntity> atualizar(@PathVariable Long id, @RequestBody Livro livro){
        return ResponseEntity.ok(livroService.atualizar(id, livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
