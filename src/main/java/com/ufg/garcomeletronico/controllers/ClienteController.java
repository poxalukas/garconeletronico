package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.ClienteDTO;
import com.ufg.garcomeletronico.services.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ClienteDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ClienteDTO create(@RequestBody ClienteDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ClienteDTO update(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
