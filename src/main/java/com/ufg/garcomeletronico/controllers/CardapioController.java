package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.CardapioDTO;
import com.ufg.garcomeletronico.services.CardapioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cardapios")
public class CardapioController {

    private final CardapioService service;

    public CardapioController(CardapioService service) {
        this.service = service;
    }

    @GetMapping
    public List<CardapioDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CardapioDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CardapioDTO create(@RequestBody CardapioDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CardapioDTO update(@PathVariable Long id, @RequestBody CardapioDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
