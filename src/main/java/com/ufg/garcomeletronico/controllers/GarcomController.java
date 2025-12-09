package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.GarcomDTO;
import com.ufg.garcomeletronico.dto.MesaDTO;
import com.ufg.garcomeletronico.services.GarcomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garcons")
public class GarcomController {

    private final GarcomService service;

    public GarcomController(GarcomService service) {
        this.service = service;
    }

    @GetMapping
    public List<GarcomDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public GarcomDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public GarcomDTO create(@RequestBody GarcomDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public GarcomDTO update(@PathVariable Long id, @RequestBody GarcomDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}/mesas")
    public List<MesaDTO> listarMesasDoGarcom(@PathVariable Long id) {
        return service.listarMesasDoGarcom(id);
    }

    @PostMapping("/{garcomId}/atribuir-mesa/{mesaId}")
    public GarcomDTO atribuirMesa(
            @PathVariable Long garcomId,
            @PathVariable Long mesaId
    ) {
        return service.atribuirMesa(garcomId, mesaId);
    }
}
