package com.ufg.garcomeletronico.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ItemPedido> itens;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Conta conta;

    private BigDecimal valorTotal;

}
