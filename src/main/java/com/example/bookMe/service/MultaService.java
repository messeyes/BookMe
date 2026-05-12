package com.example.bookMe.service;

import com.example.bookMe.controller.dto.MultaRequest;
import com.example.bookMe.repository.MultaRepository;
import com.example.bookMe.repository.entity.EmprestimoEntity;
import com.example.bookMe.repository.entity.MultaEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultaService {
    private final MultaRepository multaRepository;
    private final EmprestimoService emprestimoService;

    public MultaService(MultaRepository multaRepository,
                        EmprestimoService emprestimoService){
        this.multaRepository = multaRepository;
        this.emprestimoService = emprestimoService;
    }

    public MultaEntity criar(MultaRequest request){
        EmprestimoEntity emprestimo = emprestimoService.buscarPorId(request.getIdEmprestimo());

        multaRepository.findByEmprestimoId(emprestimo.getId()).ifPresent(m -> {
            throw new RuntimeException("Já existe uma multa para esse empréstimo!");
        });

        return multaRepository.save(new MultaEntity(emprestimo, request.getValor(), request.getTipo()));
    }

    public MultaEntity buscarPorId(Long id){
        return multaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Multa não encontrada: " + id));
    }

    public List<MultaEntity> listarTodos(){
        return multaRepository.findAll();
    }

    public MultaEntity pagar(Long id){
        MultaEntity multa = buscarPorId(id);
        multa.setPago(true);
        return multaRepository.save(multa);
    }

}
