package com.ufg.garcomeletronico.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MesaDTO {
    private Long id;
    private int numero;
    private boolean disponivel;
    private GarcomDTO garcom;
}
