package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.dto.CategoriaDTO;
import com.ufg.garcomeletronico.entities.Categoria;
import com.ufg.garcomeletronico.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<CategoriaDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO findById(Long id) {
        return toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada")));
    }

    public CategoriaDTO create(CategoriaDTO dto) {
        Categoria c = new Categoria();
        c.setNome(dto.getNome());
        c = repository.save(c);
        return toDTO(c);
    }

    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        Categoria c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        c.setNome(dto.getNome());
        c = repository.save(c);
        return toDTO(c);
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada")));
    }

    private CategoriaDTO toDTO(Categoria c) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        return dto;
    }
}
