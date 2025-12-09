package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.dto.GarcomDTO;
import com.ufg.garcomeletronico.dto.MesaDTO;
import com.ufg.garcomeletronico.entities.Garcom;
import com.ufg.garcomeletronico.entities.Mesa;
import com.ufg.garcomeletronico.repositories.GarcomRepository;
import com.ufg.garcomeletronico.repositories.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarcomService {

    @Autowired
    private GarcomRepository repository;

    @Autowired
    private MesaRepository mesaRepository;

    public List<GarcomDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public GarcomDTO findById(Long id) {
        return toDTO(
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Garçom não encontrado"))
        );
    }

    public GarcomDTO create(GarcomDTO dto) {
        Garcom garcom = repository.save(toEntity(dto));
        return toDTO(garcom);
    }

    public GarcomDTO update(Long id, GarcomDTO dto) {
        Garcom entity = toEntity(dto);
        entity.setId(id);
        return toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        repository.delete(
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Garçom não encontrado"))
        );
    }

    public List<MesaDTO> listarMesasDoGarcom(Long garcomId) {
        Garcom garcom = repository.findById(garcomId)
                .orElseThrow(() -> new RuntimeException("Garçom não encontrado"));

        return mesaRepository.findByGarcom(garcom)
                .stream()
                .map(this::toMesaDTO)
                .toList();
    }

    public GarcomDTO atribuirMesa(Long garcomId, Long mesaId) {
        Garcom garcom = repository.findById(garcomId)
                .orElseThrow(() -> new RuntimeException("Garçom não encontrado"));

        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        mesa.setGarcom(garcom);
        mesaRepository.save(mesa);

        return toDTO(garcom);
    }

    private GarcomDTO toDTO(Garcom g) {
        if (g == null) return null;
        return new GarcomDTO(
                g.getId(),
                g.getNome()
        );
    }

    private Garcom toEntity(GarcomDTO dto) {
        if (dto == null) return null;
        Garcom g = new Garcom();
        g.setId(dto.getId());
        g.setNome(dto.getNome());
        return g;
    }

    private MesaDTO toMesaDTO(Mesa m) {
        if (m == null) return null;

        return new MesaDTO(
                m.getId(),
                m.getNumero(),
                m.isDisponivel(),
                null
        );
    }
}
