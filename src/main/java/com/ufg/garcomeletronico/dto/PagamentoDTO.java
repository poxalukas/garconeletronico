package com.ufg.garcomeletronico.dto;

import com.ufg.garcomeletronico.enums.TipoPagamentoEnum;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO {
    private Long id;
    private TipoPagamentoEnum tipo;
    private Integer numeroTransacao;
    private Integer numeroCheque;
}
