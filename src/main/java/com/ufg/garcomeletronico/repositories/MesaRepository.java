package com.ufg.garcomeletronico.repositories;

import com.ufg.garcomeletronico.entities.Garcom;
import com.ufg.garcomeletronico.entities.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MesaRepository extends JpaRepository<Mesa, Long> {

    Optional<Mesa> findByNumero(int numero);

    List<Mesa> findByDisponivelTrue();

    List<Mesa> findByDisponivelFalse();

    List<Mesa> findByGarcom(Garcom garcom);

}
