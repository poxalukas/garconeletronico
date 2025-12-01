package com.ufg.garcomeletronico.repositories;

import com.ufg.garcomeletronico.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> { }
