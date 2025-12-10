package com.ufg.garcomeletronico.entities;

import com.ufg.garcomeletronico.enums.TipoPagamentoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPagamentoEnum tipo;

    private LocalDateTime horaPagamento;

    private Integer numeroTransacao;

    private Integer numeroCheque;

    private BigDecimal valorPago;
}
