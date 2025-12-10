package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.PagamentoDTO;
import com.ufg.garcomeletronico.services.PagamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<PagamentoDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PagamentoDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public PagamentoDTO create(@RequestBody PagamentoDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PagamentoDTO update(@PathVariable Long id, @RequestBody PagamentoDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @DeleteMapping("/excluirFormaPagamento/{id}")
    public void excluirFormaPagamento(@PathVariable Long id) {
        service.excluirPagamento(id);
    }
}
