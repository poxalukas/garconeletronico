package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.MesaDTO;
import com.ufg.garcomeletronico.entities.Mesa;
import com.ufg.garcomeletronico.services.MesaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
public class MesaController {

    private final MesaService service;

    public MesaController(MesaService service) {
        this.service = service;
    }

    @GetMapping
    public List<MesaDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public MesaDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public MesaDTO create(@RequestBody Mesa mesa) {
        return service.create(mesa);
    }

    @PutMapping("/{id}")
    public MesaDTO update(@PathVariable Long id, @RequestBody Mesa mesa) {
        return service.update(id, mesa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/abrir/{numeroMesa}")
    public MesaDTO abrirMesa(@PathVariable int numeroMesa) {
        return service.abrirMesa(numeroMesa);
    }

    @PostMapping("/fechar/{idMesa}")
    public MesaDTO fecharMesa(@PathVariable Long idMesa) {
        return service.fecharMesa(idMesa);
    }

    @PostMapping("/atribuir-garcom/{idMesa}/{idGarcom}")
    public MesaDTO atribuirGarcom(@PathVariable Long idMesa, @PathVariable Long idGarcom) {
        return service.atribuirGarcom(idMesa, idGarcom);
    }

    @PostMapping("/liberar/{idMesa}")
    public MesaDTO liberarMesa(@PathVariable Long idMesa) {
        return service.liberarMesa(idMesa);
    }

    @GetMapping("/disponiveis")
    public List<MesaDTO> listarDisponiveis() {
        return service.listarDisponiveis();
    }

    @GetMapping("/ocupadas")
    public List<MesaDTO> listarOcupadas() {
        return service.listarOcupadas();
    }
}
