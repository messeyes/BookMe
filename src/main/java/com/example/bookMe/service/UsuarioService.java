package com.example.bookMe.service;

import com.example.bookMe.domain.Usuario;
import com.example.bookMe.repository.UsuarioRepository;
import com.example.bookMe.repository.entity.UsuarioEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioEntity criar(Usuario request){
        return usuarioRepository.save(new UsuarioEntity(request));
    }

    public UsuarioEntity buscarPorId(Long id){
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(()->
                new RuntimeException("Usuário não encontrado: " + id));
    }

    public List<UsuarioEntity> listarTodosUsuarios(){
        return usuarioRepository.findAll();
    }

    public UsuarioEntity atualizar(Long id, Usuario usuario){
        UsuarioEntity Usuario = buscarPorId(id);
        usuario.nome();
        usuario.email();
        usuario.senha();
        usuario.dataCadastro();
        usuario.cargo();

        return usuarioRepository.save(new UsuarioEntity(usuario));
    }

    public void deletar(Long id){
        buscarPorId(id);
        usuarioRepository.deleteById(id);
    }

}
