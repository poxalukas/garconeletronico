package com.ufg.garcomeletronico.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;

    private LocalDateTime horaPedido;
    private LocalDateTime horaEntrega;

    @ManyToOne
    private Cliente cliente;

    @OneToMany
    private List<ItemPedido> itens;

    @ManyToOne
    private Conta conta;
}
