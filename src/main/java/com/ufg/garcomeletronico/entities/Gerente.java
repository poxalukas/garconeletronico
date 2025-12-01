package com.ufg.garcomeletronico.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gerentes")
public class Gerente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Usuario usuario;
}
