package com.ufg.garcomeletronico.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Mesa mesa;

    @OneToMany(mappedBy = "conta")
    private List<Pedido> pedidos;

    @OneToOne
    private Pagamento pagamento;
}
