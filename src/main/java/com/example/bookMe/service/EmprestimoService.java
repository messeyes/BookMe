package com.example.bookMe.service;

import com.example.bookMe.controller.dto.EmprestimoRequest;
import com.example.bookMe.domain.Emprestimo;
import com.example.bookMe.repository.EmprestimoRepository;
import com.example.bookMe.repository.LivroRepository;
import com.example.bookMe.repository.entity.EmprestimoEntity;
import com.example.bookMe.repository.entity.LivroEntity;
import com.example.bookMe.repository.entity.UsuarioEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final LivroService livroService;
    private final UsuarioService usuarioService;
    private final LivroRepository livroRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             LivroService livroService,
                             UsuarioService usuarioService, LivroRepository livroRepository){
        this.emprestimoRepository = emprestimoRepository;
        this.livroService = livroService;
        this.usuarioService = usuarioService;
        this.livroRepository = livroRepository;
    }

    public EmprestimoEntity criar(EmprestimoRequest request){
        UsuarioEntity usuario = usuarioService.buscarPorId(request.getIdUsuario());
        LivroEntity livro = livroService.buscarPorId(request.getIdLivro());

        if(livro.getQuantDisponivel() <= 0){
            throw new RuntimeException("Livro sem exemplares disponíveis!");
        }

        livro.setQuantDisponivel(livro.getQuantDisponivel() - 1);

        EmprestimoEntity emprestimo = new EmprestimoEntity(usuario, livro);
        return emprestimoRepository.save(emprestimo);
    }

    public EmprestimoEntity buscarPorId(Long id){
        Optional<EmprestimoEntity> emprestimo = emprestimoRepository.findById(id);
        return emprestimo.orElseThrow(()->
                new RuntimeException("Emprestimo não encontrado: " +id));
    }

    public List<EmprestimoEntity> listarTodos(){
        return emprestimoRepository.findAll();
    }

    public EmprestimoEntity devolver(Long id){
        EmprestimoEntity emprestimo = buscarPorId(id);

        emprestimo.setDataDevolReal(Instant.now());
        LivroEntity livro = emprestimo.getLivro();
        livro.setQuantDisponivel(livro.getQuantDisponivel() + 1);

        return emprestimoRepository.save(emprestimo);
    }
}
