package com.ufg.garcomeletronico.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CardapioDTO {
    private Long id;
    private List<ItemCardapioDTO> itens;
}
