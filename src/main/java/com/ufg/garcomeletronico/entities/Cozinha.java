package com.ufg.garcomeletronico.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cozinha")
public class Cozinha extends Usuario { }
