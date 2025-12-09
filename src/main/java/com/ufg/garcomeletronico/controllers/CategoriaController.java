package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.CategoriaDTO;
import com.ufg.garcomeletronico.services.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoriaDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CategoriaDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CategoriaDTO create(@RequestBody CategoriaDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CategoriaDTO update(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
