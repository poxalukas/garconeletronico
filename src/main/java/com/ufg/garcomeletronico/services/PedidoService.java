package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.converters.EntityDTOConverter;
import com.ufg.garcomeletronico.dto.PedidoDTO;
import com.ufg.garcomeletronico.entities.Pedido;
import com.ufg.garcomeletronico.entities.Conta;
import com.ufg.garcomeletronico.entities.ItemPedido;
import com.ufg.garcomeletronico.repositories.ContaRepository;
import com.ufg.garcomeletronico.repositories.ItemPedidoRepository;
import com.ufg.garcomeletronico.repositories.PedidoRepository;
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
    private  ContaRepository contaRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private EntityDTOConverter converter;

    public List<PedidoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(converter::toPedidoDTO)
                .collect(Collectors.toList());
    }

    public PedidoDTO findById(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return converter.toPedidoDTO(pedido);
    }

    public PedidoDTO create(PedidoDTO dto) {
        Pedido pedido = converter.toEntity(dto);
        return converter.toPedidoDTO(repository.save(pedido));
    }

    public PedidoDTO update(Long id, PedidoDTO dto) {
        Pedido pedido = converter.toEntity(dto);
        pedido.setId(id);
        return converter.toPedidoDTO(repository.save(pedido));
    }

    public void delete(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        repository.delete(pedido);
    }

    public PedidoDTO criarPedidoParaConta(Long contaId, PedidoDTO pedidoDTO) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        Pedido pedido = converter.toEntity(pedidoDTO);
        pedido.setConta(conta);
        pedido.setHoraPedido(LocalDateTime.now());

        return converter.toPedidoDTO(repository.save(pedido));
    }

    public PedidoDTO adicionarItem(Long pedidoId, Long itemId) {
        Pedido pedido = repository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        ItemPedido item = itemPedidoRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        pedido.getItens().add(item);
        return converter.toPedidoDTO(repository.save(pedido));
    }

    public PedidoDTO marcarComoEntregue(Long pedidoId) {
        Pedido pedido = repository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setHoraEntrega(LocalDateTime.now());
        return converter.toPedidoDTO(repository.save(pedido));
    }

    public List<PedidoDTO> listarPorConta(Long contaId) {
        return repository.findByContaId(contaId)
                .stream()
                .map(converter::toPedidoDTO)
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

    public PedidoDTO criarPedido(PedidoDTO dto) {
        Pedido pedido = converter.toEntity(dto);
        return converter.toPedidoDTO(repository.save(pedido));
    }

    public PedidoDTO atualizarPedido(Long id, PedidoDTO dto) {
        Pedido pedido = converter.toEntity(dto);
        pedido.setId(id);
        return converter.toPedidoDTO(repository.save(pedido));
    }

    public void excluirPedido(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        repository.delete(pedido);
    }

    public List<PedidoDTO> buscarTodos() {
        return repository.findAll()
                .stream()
                .map(converter::toPedidoDTO)
                .collect(Collectors.toList());
    }

}
