package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.ItemCardapioDTO;
import com.ufg.garcomeletronico.services.ItemCardapioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-cardapio")
public class ItemCardapioController {

    private final ItemCardapioService service;

    public ItemCardapioController(ItemCardapioService service) {
        this.service = service;
    }

    @GetMapping
    public List<ItemCardapioDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ItemCardapioDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ItemCardapioDTO create(@RequestBody ItemCardapioDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ItemCardapioDTO update(@PathVariable Long id, @RequestBody ItemCardapioDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Listar itens do cardápio filtrando por ingredientes
    @PostMapping("/listarItensPorIngredientes")
    public List<ItemCardapioDTO> listarItensPorIngredientes(@RequestBody List<String> ingredientes) {
        return itemCardapioService.listarPorIngredientes(ingredientes);
    }
}
