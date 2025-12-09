package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.dto.PagamentoDTO;
import com.ufg.garcomeletronico.entities.Pagamento;
import com.ufg.garcomeletronico.repositories.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;

    public PagamentoService(PagamentoRepository repository) {
        this.repository = repository;
    }

    public List<PagamentoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public PagamentoDTO findById(Long id) {
        return toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado")));
    }

    public PagamentoDTO create(PagamentoDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public PagamentoDTO update(Long id, PagamentoDTO dto) {
        Pagamento entity = toEntity(dto);
        entity.setId(id);
        return toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        Pagamento entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        repository.delete(entity);
    }

    private PagamentoDTO toDTO(Pagamento entity) {
        return new PagamentoDTO(
                entity.getId(),
                entity.getTipo(),
                entity.getNumeroTransacao(),
                entity.getNumeroCheque()
        );
    }

    private Pagamento toEntity(PagamentoDTO dto) {
        Pagamento entity = new Pagamento();
        entity.setId(dto.getId());
        entity.setTipo(dto.getTipo());
        entity.setNumeroTransacao(dto.getNumeroTransacao());
        entity.setNumeroCheque(dto.getNumeroCheque());
        return entity;
    }
}
