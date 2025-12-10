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
public class MesaService {

    @Autowired
    private MesaRepository repository;

    @Autowired
    private GarcomRepository garcomRepository;

    private List<MesaDTO> toDTOList(List<Mesa> list) {
        return list.stream().map(this::toDTO).toList();
    }

    public List<MesaDTO> findAll() {
        return toDTOList(repository.findAll());
    }

    public MesaDTO findById(Long id) {
        return toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada")));
    }

    public MesaDTO create(Mesa obj) {
        return toDTO(repository.save(obj));
    }

    public MesaDTO update(Long id, Mesa obj) {
        obj.setId(id);
        return toDTO(repository.save(obj));
    }

    public void delete(Long id) {
        repository.delete(
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Mesa não encontrada"))
        );
    }

    public MesaDTO abrirMesa(int numeroMesa) {
        Mesa mesa = repository.findByNumero(numeroMesa)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        if (!mesa.isDisponivel())
            throw new RuntimeException("Mesa já está ocupada");

        mesa.setDisponivel(false);
        return toDTO(repository.save(mesa));
    }

    public MesaDTO fecharMesa(Long idMesa) {
        Mesa mesa = repository.findById(idMesa)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        mesa.setDisponivel(true);
        mesa.setGarcom(null);

        return toDTO(repository.save(mesa));
    }

    public MesaDTO atribuirGarcom(Long idMesa, Long idGarcom) {
        Mesa mesa = repository.findById(idMesa)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        Garcom garcom = garcomRepository.findById(idGarcom)
                .orElseThrow(() -> new RuntimeException("Garçom não encontrado"));

        mesa.setGarcom(garcom);
        mesa.setDisponivel(false);

        return toDTO(repository.save(mesa));
    }

    public MesaDTO liberarMesa(Long idMesa) {
        Mesa mesa = repository.findById(idMesa)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        mesa.setGarcom(null);
        mesa.setDisponivel(true);

        return toDTO(repository.save(mesa));
    }

    public List<MesaDTO> listarDisponiveis() {
        return toDTOList(repository.findByDisponivelTrue());
    }

    public List<MesaDTO> listarOcupadas() {
        return toDTOList(repository.findByDisponivelFalse());
    }

    private MesaDTO toDTO(Mesa m) {
        if (m == null) return null;

        GarcomDTO garcomDTO = null;
        if (m.getGarcom() != null) {
            garcomDTO = new GarcomDTO(
                    m.getGarcom().getId(),
                    m.getGarcom().getNome()
            );
        }

        return new MesaDTO(
                m.getId(),
                m.getNumero(),
                m.isDisponivel(),
                garcomDTO
        );
    }
    public List<MesaDTO> buscarMesas() {
        return toDTOList(repository.findAll());
    }

}
