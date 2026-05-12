package com.example.bookMe.service;

import com.example.bookMe.controller.dto.ReservaRequest;
import com.example.bookMe.repository.ReservaRepository;
import com.example.bookMe.repository.entity.LivroEntity;
import com.example.bookMe.repository.entity.ReservaEntity;
import com.example.bookMe.repository.entity.UsuarioEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final UsuarioService usuarioService;
    private final LivroService livroService;

    public ReservaService(ReservaRepository reservaRepository,
                          UsuarioService usuarioService,
                          LivroService livroService){
        this.reservaRepository = reservaRepository;
        this.usuarioService = usuarioService;
        this.livroService = livroService;
    }

    public ReservaEntity criar(ReservaRequest request){
        UsuarioEntity usuario = usuarioService.buscarPorId(request.getIdUsuario());
        LivroEntity livro = livroService.buscarPorId(request.getIdLivro());

        int ordemFila = reservaRepository.countByLivroId(livro.getId()) + 1;

        return reservaRepository.save(new ReservaEntity(usuario, livro, ordemFila));
    }

    public ReservaEntity buscarPorId(Long id){
        return reservaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Reserva não encontrada: " + id));
    }

    public List<ReservaEntity> listarTodos(){
        return reservaRepository.findAll();
    }

    public void cancelar(Long id){
        ReservaEntity reserva = buscarPorId(id);
        reserva.setStatus("CANCELADA");
        reservaRepository.save(reserva);
    }
}
