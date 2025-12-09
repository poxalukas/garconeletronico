package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.dto.RestauranteDTO;
import com.ufg.garcomeletronico.entities.Restaurante;
import com.ufg.garcomeletronico.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    public List<RestauranteDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public RestauranteDTO findById(Long id) {
        return toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado")));
    }

    public RestauranteDTO create(RestauranteDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public RestauranteDTO update(Long id, RestauranteDTO dto) {
        Restaurante entity = toEntity(dto);
        entity.setId(id);
        return toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado")));
    }

    private RestauranteDTO toDTO(Restaurante entity) {
        RestauranteDTO dto = new RestauranteDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        return dto;
    }

    private Restaurante toEntity(RestauranteDTO dto) {
        Restaurante entity = new Restaurante();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        return entity;
    }
}
