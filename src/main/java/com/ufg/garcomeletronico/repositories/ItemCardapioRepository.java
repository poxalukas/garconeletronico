package com.ufg.garcomeletronico.repositories;

import com.ufg.garcomeletronico.entities.ItemCardapio;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
    @Query("SELECT i FROM ItemCardapio i WHERE " +
            "LOWER(i.ingredientes) LIKE %:ingrediente%")
    List<ItemCardapio> findByIngrediente(@Param("ingrediente") String ingrediente);
}
