package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.ItemPedidoDTO;
import com.ufg.garcomeletronico.services.ItemPedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-pedido")
public class ItemPedidoController {

    private final ItemPedidoService service;

    public ItemPedidoController(ItemPedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ItemPedidoDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ItemPedidoDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ItemPedidoDTO create(@RequestBody ItemPedidoDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ItemPedidoDTO update(@PathVariable Long id, @RequestBody ItemPedidoDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
