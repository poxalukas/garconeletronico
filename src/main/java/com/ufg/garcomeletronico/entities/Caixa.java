package com.ufg.garcomeletronico.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "caixas")
public class Caixa extends Usuario { }
