package com.ufg.garcomeletronico.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "garcons")
public class Garcom extends Usuario { }
