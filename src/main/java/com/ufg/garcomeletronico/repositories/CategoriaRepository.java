package com.ufg.garcomeletronico.repositories;

import com.ufg.garcomeletronico.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> { }
