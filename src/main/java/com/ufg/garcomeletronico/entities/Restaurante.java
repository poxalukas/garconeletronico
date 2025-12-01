package com.ufg.garcomeletronico.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "restaurantes")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}
