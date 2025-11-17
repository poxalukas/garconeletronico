package com.ufg.garcomeletronico.dto;

import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {
    private Long id;
    private String nome;
    private MesaDTO mesa;
    private List<PedidoDTO> pedidos;
    private PagamentoDTO pagamento;
}
