package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.dto.CozinhaDTO;
import com.ufg.garcomeletronico.entities.Cozinha;
import com.ufg.garcomeletronico.repositories.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository repository;


    public List<CozinhaDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public CozinhaDTO findById(Long id) {
        Cozinha cozinha = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cozinha não encontrada"));

        return toDTO(cozinha);
    }

    public CozinhaDTO create(CozinhaDTO dto) {
        Cozinha cozinha = toEntity(dto);
        return toDTO(repository.save(cozinha));
    }

    public CozinhaDTO update(Long id, CozinhaDTO dto) {
        Cozinha cozinha = toEntity(dto);
        cozinha.setId(id);
        return toDTO(repository.save(cozinha));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


    private CozinhaDTO toDTO(Cozinha c) {
        if (c == null) return null;
        return new CozinhaDTO(c.getId(), c.getNome());
    }

    private Cozinha toEntity(CozinhaDTO dto) {
        if (dto == null) return null;
        Cozinha c = new Cozinha();
        c.setId(dto.getId());
        c.setNome(dto.getNome());
        return c;
    }
}
