package com.ufg.garcomeletronico.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float quantidade;

    @ManyToOne
    private ItemCardapio itemCardapio;
}
