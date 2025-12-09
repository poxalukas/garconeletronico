package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.dto.CaixaDTO;
import com.ufg.garcomeletronico.entities.Caixa;
import com.ufg.garcomeletronico.repositories.CaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaixaService {

    @Autowired
    private CaixaRepository repository;

    public List<CaixaDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(CaixaDTO::toDTO)
                .collect(Collectors.toList());
    }

    public CaixaDTO findById(Long id) {
        Caixa caixa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caixa não encontrado"));

        return CaixaDTO.toDTO(caixa);
    }

    public CaixaDTO create(CaixaDTO dto) {
        Caixa caixa = new Caixa();
        caixa.setNome(dto.getNome());
        caixa.setLogin(dto.getLogin());
        caixa.setSenha(dto.getSenha());

        caixa = repository.save(caixa);

        return CaixaDTO.toDTO(caixa);
    }

    public CaixaDTO update(Long id, CaixaDTO dto) {
        Caixa caixa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caixa não encontrado"));

        caixa.setNome(dto.getNome());
        caixa.setLogin(dto.getLogin());

        // atualizar senha apenas se enviada
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            caixa.setSenha(dto.getSenha());
        }

        caixa = repository.save(caixa);

        return CaixaDTO.toDTO(caixa);
    }

    public void delete(Long id) {
        Caixa caixa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caixa não encontrado"));

        repository.delete(caixa);
    }
}
