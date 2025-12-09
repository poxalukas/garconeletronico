package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.dto.GerenteDTO;
import com.ufg.garcomeletronico.entities.Gerente;
import com.ufg.garcomeletronico.entities.Usuario;
import com.ufg.garcomeletronico.repositories.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository repository;

    public List<GerenteDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public GerenteDTO findById(Long id) {
        return toDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Gerente não encontrado")));
    }

    public GerenteDTO create(GerenteDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public GerenteDTO update(Long id, GerenteDTO dto) {
        Gerente entity = toEntity(dto);
        entity.setId(id);
        return toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new RuntimeException("Gerente não encontrado")));
    }

    private GerenteDTO toDTO(Gerente g) {
        return new GerenteDTO(
                g.getId(),
                g.getUsuario().getNome()
        );
    }

    private Gerente toEntity(GerenteDTO dto) {
        Gerente g = new Gerente();
        g.setId(dto.getId());

        Usuario u = new Usuario();
        u.setId(dto.getId());
        u.setNome(dto.getNome());

        g.setUsuario(u);
        return g;
    }
}
