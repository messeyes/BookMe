package com.example.bookMe.service;

import com.example.bookMe.domain.Livro;
import com.example.bookMe.repository.entity.LivroEntity;
import com.example.bookMe.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }


    public LivroEntity criar(Livro livro) {
        return livroRepository.save(new LivroEntity(livro));
    }

    public LivroEntity buscarPorId(Long id){
        Optional<LivroEntity> livro = livroRepository.findById(id);
        return livro.orElseThrow(()->
            new RuntimeException( "Livro não encontrado: " + id));
    }

    public LivroEntity buscarPorIsbn(Long isbn) {
        Optional<LivroEntity> Livro = livroRepository.findByIsbn(isbn);
        return Livro.orElseThrow(() ->
                new RuntimeException("Livro não encontrado: " + isbn));
    }

    public List<LivroEntity> listarTodos() {
        return livroRepository.findAll();
    }

    public LivroEntity atualizar(Long id, Livro livro){
        LivroEntity Livro = buscarPorId(id);
        livro.titulo();
        livro.autor();
        livro.isbn();
        livro.quantTotal();

        return livroRepository.save(new LivroEntity(livro));
    }

    public void deletar(Long id){
        buscarPorId(id);
        livroRepository.deleteById(id);

    }
}
