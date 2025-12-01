package com.ufg.garcomeletronico.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mesas")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;
    private boolean disponivel;

    @ManyToOne
    private Garcom garcom;
}
