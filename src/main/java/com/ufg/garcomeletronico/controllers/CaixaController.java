package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.CaixaDTO;
import com.ufg.garcomeletronico.services.CaixaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/caixas")
public class CaixaController {

    private final CaixaService service;

    public CaixaController(CaixaService service) {
        this.service = service;
    }

    @GetMapping
    public List<CaixaDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CaixaDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CaixaDTO create(@RequestBody CaixaDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CaixaDTO update(@PathVariable Long id, @RequestBody CaixaDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
