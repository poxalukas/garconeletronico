package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.RestauranteDTO;
import com.ufg.garcomeletronico.services.RestauranteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    private final RestauranteService service;

    public RestauranteController(RestauranteService service) {
        this.service = service;
    }

    @GetMapping
    public List<RestauranteDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RestauranteDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public RestauranteDTO create(@RequestBody RestauranteDTO restaurante) {
        return service.create(restaurante);
    }

    @PutMapping("/{id}")
    public RestauranteDTO update(@PathVariable Long id, @RequestBody RestauranteDTO restaurante) {
        return service.update(id, restaurante);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
