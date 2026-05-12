package com.example.bookMe.controller;

import com.example.bookMe.controller.dto.ReservaRequest;
import com.example.bookMe.service.ReservaService;
import com.example.bookMe.repository.entity.ReservaEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService){
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<ReservaEntity> criar(@RequestBody @Valid ReservaRequest request){
        return ResponseEntity.status(201).body(reservaService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<ReservaEntity>> listarTodos(){
        return ResponseEntity.ok(reservaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaEntity> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(reservaService.buscarPorId(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id){
        reservaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
