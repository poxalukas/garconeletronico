package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.PedidoDTO;
import com.ufg.garcomeletronico.services.PedidoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<PedidoDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PedidoDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public PedidoDTO create(@RequestBody PedidoDTO pedido) {
        return service.create(pedido);
    }

    @PutMapping("/{id}")
    public PedidoDTO update(@PathVariable Long id, @RequestBody PedidoDTO pedido) {
        return service.update(id, pedido);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/conta/{contaId}/novo")
    public PedidoDTO criarPedido(@PathVariable Long contaId, @RequestBody PedidoDTO pedido) {
        return service.criarPedidoParaConta(contaId, pedido);
    }

    @PostMapping("/{pedidoId}/add-item/{itemId}")
    public PedidoDTO adicionarItem(@PathVariable Long pedidoId, @PathVariable Long itemId) {
        return service.adicionarItem(pedidoId, itemId);
    }

    @PostMapping("/{pedidoId}/entregar")
    public PedidoDTO marcarComoEntregue(@PathVariable Long pedidoId) {
        return service.marcarComoEntregue(pedidoId);
    }

    @GetMapping("/conta/{contaId}")
    public List<PedidoDTO> listarPorConta(@PathVariable Long contaId) {
        return service.listarPorConta(contaId);
    }

    @PostMapping("/criarPedido")
    public PedidoDTO criarPedido(@RequestBody PedidoDTO dto) {
        return pedidoService.criarPedido(dto);
    }
    
    @PutMapping("/atualizarPedido/{id}")
    public PedidoDTO atualizarPedido(@PathVariable Long id, @RequestBody PedidoDTO dto) {
        return pedidoService.atualizarPedido(id, dto);
    }
}
