package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.GerenteDTO;
import com.ufg.garcomeletronico.services.GerenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gerentes")
public class GerenteController {

    private final GerenteService service;

    public GerenteController(GerenteService service) {
        this.service = service;
    }

    @GetMapping
    public List<GerenteDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public GerenteDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public GerenteDTO create(@RequestBody GerenteDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public GerenteDTO update(@PathVariable Long id, @RequestBody GerenteDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
