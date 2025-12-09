package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.dto.PedidoDTO;
import com.ufg.garcomeletronico.entities.*;
import com.ufg.garcomeletronico.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<PedidoDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PedidoDTO findById(Long id) {
        return toDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado")));
    }

    public PedidoDTO create(PedidoDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public PedidoDTO update(Long id, PedidoDTO dto) {
        Pedido entity = toEntity(dto);
        entity.setId(id);
        return toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado")));
    }

    public PedidoDTO criarPedidoParaConta(Long contaId, PedidoDTO pedidoDTO) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        Pedido pedido = toEntity(pedidoDTO);
        pedido.setConta(conta);
        pedido.setHoraPedido(LocalDateTime.now());

        return toDTO(repository.save(pedido));
    }

    public PedidoDTO adicionarItem(Long pedidoId, Long itemId) {
        Pedido pedido = repository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        ItemPedido item = itemPedidoRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        pedido.getItens().add(item);

        return toDTO(repository.save(pedido));
    }

    public PedidoDTO marcarComoEntregue(Long pedidoId) {
        Pedido pedido = repository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setHoraEntrega(LocalDateTime.now());

        return toDTO(repository.save(pedido));
    }

    public List<PedidoDTO> listarPorConta(Long contaId) {
        return repository.findByContaId(contaId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private PedidoDTO toDTO(Pedido entity) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(entity.getId());
        dto.setNumero(entity.getNumero());
        dto.setHoraPedido(entity.getHoraPedido());
        dto.setHoraEntrega(entity.getHoraEntrega());
        return dto;
    }

    private Pedido toEntity(PedidoDTO dto) {
        Pedido e = new Pedido();
        e.setId(dto.getId());
        e.setNumero(dto.getNumero());
        e.setHoraPedido(dto.getHoraPedido());
        e.setHoraEntrega(dto.getHoraEntrega());
        return e;
    }
}
