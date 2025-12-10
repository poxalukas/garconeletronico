package com.ufg.garcomeletronico.controllers;

import com.ufg.garcomeletronico.dto.ContaDTO;
import com.ufg.garcomeletronico.dto.PagamentoDTO;
import com.ufg.garcomeletronico.dto.PedidoDTO;
import com.ufg.garcomeletronico.entities.Conta;
import com.ufg.garcomeletronico.entities.Pagamento;
import com.ufg.garcomeletronico.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService service;

    @GetMapping
    public List<ContaDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ContaDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/create")
    public ContaDTO create(@RequestBody ContaDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/update/{id}")
    public ContaDTO update(@PathVariable Long id, @RequestBody ContaDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // -------------------- ROTAS PERSONALIZADAS --------------------

    @GetMapping("/mesa/{mesaId}")
    public List<ContaDTO> findByMesa(@PathVariable Long mesaId) {
        return service.findByMesa(mesaId);
    }

    @GetMapping("/{id}/pedidos")
    public List<PedidoDTO> getPedidosDaConta(@PathVariable Long id) {
        return service.getPedidos(id);
    }

    @PostMapping("/abrir/{mesaId}")
    public ContaDTO abrirConta(@PathVariable Long mesaId, @RequestBody ContaDTO dto) {
        return service.abrirConta(mesaId, dto);
    }

    @PostMapping("/fechar/{id}")
    public ContaDTO fecharConta(@PathVariable Long id) {
        return service.fecharConta(id);
    }

    @PostMapping("/{id}/pagamento")
    public ContaDTO registrarPagamento(@PathVariable Long id, @RequestBody PagamentoDTO pagamento) {
        return service.registrarPagamento(id, pagamento);
    }

    // Buscar todas as mesas
    @GetMapping("/buscarMesas")
    public List<MesaDTO> buscarMesas() {
        return contaService.buscarMesas();
    }

    // Listar mesas atendidas por garçom
    @PostMapping("/listarMesasAtendidasGarcon")
    public List<ContaDTO> listarMesasAtendidasGarcon(@RequestParam Long garcomId) {
        return contaService.listarMesasAtendidasGarcon(garcomId);
    }

    // Buscar conta de um cliente específico
    @GetMapping("/buscarContaCliente/{clienteId}")
    public ContaDTO buscarContaCliente(@PathVariable Long clienteId) {
        return contaService.buscarContaPorCliente(clienteId);
    }

    // Enviar conta para o caixa
    @PostMapping("/enviarContaParaCaixa/{contaId}")
    public void enviarContaParaCaixa(@PathVariable Long contaId) {
        caixaService.receberConta(contaId);
    }

}
