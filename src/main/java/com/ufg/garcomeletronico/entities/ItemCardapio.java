package com.ufg.garcomeletronico.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "itens_cardapio")
public class ItemCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String ingredientes;
    private BigDecimal preco;
    private boolean disponivelCozinha;

    @ManyToOne
    private Categoria categoria;

}
