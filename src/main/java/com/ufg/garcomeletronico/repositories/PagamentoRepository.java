package com.ufg.garcomeletronico.repositories;

import com.ufg.garcomeletronico.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> { }
