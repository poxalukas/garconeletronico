package com.ufg.garcomeletronico.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO {
    private Long id;
    private float quantidade;
    private ItemCardapioDTO itemCardapio;

}