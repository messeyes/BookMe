package com.example.bookMe.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class MultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_emprestimo", nullable = false)
    private  EmprestimoEntity emprestimo;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private boolean pago;

    @Column(nullable = false)
    private String tipo;

    public MultaEntity(){}

    public MultaEntity(EmprestimoEntity emprestimo, BigDecimal valor, String tipo){
        this.emprestimo = emprestimo;
        this.valor = valor;
        this.tipo = tipo;
        this.pago = false;
    }


}
