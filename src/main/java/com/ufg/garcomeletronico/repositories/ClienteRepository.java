package com.ufg.garcomeletronico.repositories;

import com.ufg.garcomeletronico.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> { }
