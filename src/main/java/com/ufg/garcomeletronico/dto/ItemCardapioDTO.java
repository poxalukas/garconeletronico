package com.ufg.garcomeletronico.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCardapioDTO {
    private Long id;
    private String nome;
    private String ingredientes;
    private BigDecimal preco;
    private boolean disponivelCozinha;
    private CategoriaDTO categoria;
}

