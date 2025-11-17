package com.ufg.garcomeletronico.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private int numero;
    private LocalDateTime horaPedido;
    private LocalDateTime horaEntrega;
    private ClienteDTO cliente;
    private List<ItemPedidoDTO> itens;
    private ContaDTO conta;
}
