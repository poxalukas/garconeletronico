package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.converters.EntityDTOConverter;
import com.ufg.garcomeletronico.dto.ItemPedidoDTO;
import com.ufg.garcomeletronico.entities.ItemPedido;
import com.ufg.garcomeletronico.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository repository;
    private final EntityDTOConverter converter;

    public ItemPedidoService(ItemPedidoRepository repository, EntityDTOConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<ItemPedidoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(converter::toItemPedidoDTO)
                .collect(Collectors.toList());
    }

    public ItemPedidoDTO findById(Long id) {
        return converter.toItemPedidoDTO(
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"))
        );
    }

    public ItemPedidoDTO create(ItemPedidoDTO dto) {
        ItemPedido entity = converter.toItemPedido(dto);
        return converter.toItemPedidoDTO(repository.save(entity));
    }

    public ItemPedidoDTO update(Long id, ItemPedidoDTO dto) {
        ItemPedido entity = converter.toItemPedido(dto);
        entity.setId(id);
        return converter.toItemPedidoDTO(repository.save(entity));
    }

    public void delete(Long id) {
        ItemPedido entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"));
        repository.delete(entity);
    }
}

