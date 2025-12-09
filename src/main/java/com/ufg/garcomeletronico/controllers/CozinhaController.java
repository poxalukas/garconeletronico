package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.CozinhaDTO;
import com.ufg.garcomeletronico.services.CozinhaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cozinhas")
public class CozinhaController {

    private final CozinhaService service;

    public CozinhaController(CozinhaService service) {
        this.service = service;
    }

    @GetMapping
    public List<CozinhaDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CozinhaDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CozinhaDTO create(@RequestBody CozinhaDTO cozinha) {
        return service.create(cozinha);
    }

    @PutMapping("/{id}")
    public CozinhaDTO update(@PathVariable Long id, @RequestBody CozinhaDTO cozinha) {
        return service.update(id, cozinha);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
