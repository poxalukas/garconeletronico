package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.dto.ClienteDTO;
import com.ufg.garcomeletronico.entities.Cliente;
import com.ufg.garcomeletronico.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<ClienteDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO findById(Long id) {
        return toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado")));
    }

    public ClienteDTO create(ClienteDTO dto) {
        Cliente c = new Cliente();
        c.setNome(dto.getNome());
        c = repository.save(c);
        return toDTO(c);
    }

    public ClienteDTO update(Long id, ClienteDTO dto) {
        Cliente c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        c.setNome(dto.getNome());
        c = repository.save(c);
        return toDTO(c);
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado")));
    }

    private ClienteDTO toDTO(Cliente c) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        return dto;
    }
}
