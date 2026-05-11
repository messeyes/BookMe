package com.example.bookMe.controller;

import com.example.bookMe.controller.dto.MultaRequest;
import com.example.bookMe.repository.entity.MultaEntity;
import com.example.bookMe.service.MultaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/multas")
public class MultaController {
    private final MultaService multaService;

    public MultaController(MultaService multaService){
        this.multaService = multaService;
    }

    @PostMapping
    public ResponseEntity<MultaEntity> criar(@RequestBody @Valid MultaRequest request){
        return ResponseEntity.status(201).body(multaService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<MultaEntity>> listarTodos(){
        return ResponseEntity.ok(multaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MultaEntity> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(multaService.buscarPorId(id));
    }

    @PatchMapping("/{id}/pagar")
    public ResponseEntity<MultaEntity> pagar(@PathVariable Long id){
        return ResponseEntity.ok(multaService.pagar(id));
    }
}
