package com.ufg.garcomeletronico.repositories;

import com.ufg.garcomeletronico.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    List<Conta> findByMesaId(Long mesaId);

}
