package com.ufg.garcomeletronico.entities;

import com.ufg.garcomeletronico.enums.TipoPagamentoEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPagamentoEnum tipo;

    private Integer numeroTransacao;

    private Integer numeroCheque;
}
