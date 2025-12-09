package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.converters.EntityDTOConverter;
import com.ufg.garcomeletronico.dto.ItemPedidoDTO;
import com.ufg.garcomeletronico.entities.ItemPedido;
import com.ufg.garcomeletronico.repositories.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository repository;

    public ItemPedidoService(ItemPedidoRepository repository) {
        this.repository = repository;
    }

    public List<ItemPedidoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(EntityDTOConverter::toItemPedidoDTO)
                .collect(Collectors.toList());
    }

    public ItemPedidoDTO findById(Long id) {
        return EntityDTOConverter.toItemPedidoDTO(
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"))
        );
    }

    public ItemPedidoDTO create(ItemPedidoDTO dto) {
        ItemPedido entity = EntityDTOConverter.toEntity(dto);
        return EntityDTOConverter.toItemPedidoDTO(repository.save(entity));
    }

    public ItemPedidoDTO update(Long id, ItemPedidoDTO dto) {
        ItemPedido entity = EntityDTOConverter.toEntity(dto);
        entity.setId(id);
        return EntityDTOConverter.toItemPedidoDTO(repository.save(entity));
    }

    public void delete(Long id) {
        ItemPedido entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"));
        repository.delete(entity);
    }
}
