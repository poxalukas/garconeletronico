package com.ufg.garcomeletronico.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private LocalDateTime horaChegada;
    private LocalDateTime horaSaida;
}