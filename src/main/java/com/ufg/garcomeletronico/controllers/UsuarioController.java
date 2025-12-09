package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.UsuarioDTO;
import com.ufg.garcomeletronico.services.UsuarioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsuarioDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UsuarioDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/login/{login}")
    public UsuarioDTO findByLogin(@PathVariable String login) {
        return service.findByLogin(login);
    }

    @PostMapping
    public UsuarioDTO create(@RequestBody UsuarioDTO usuario) {
        return service.create(usuario);
    }

    @PutMapping("/{id}")
    public UsuarioDTO update(@PathVariable Long id, @RequestBody UsuarioDTO usuario) {
        return service.update(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
