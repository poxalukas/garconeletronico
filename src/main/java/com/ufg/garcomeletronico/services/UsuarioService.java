package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.dto.UsuarioDTO;
import com.ufg.garcomeletronico.entities.Usuario;
import com.ufg.garcomeletronico.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO findById(Long id) {
        return toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
    }

    public UsuarioDTO findByLogin(String login) {
        return toDTO(repository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado pelo login")));
    }

    public UsuarioDTO create(UsuarioDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Dados do usuário são obrigatórios");

        if (dto.getLogin() == null || dto.getLogin().isBlank())
            throw new RuntimeException("Login é obrigatório");

        if (repository.existsByLogin(dto.getLogin())) {
            throw new RuntimeException("Já existe um usuário com esse login.");
        }

        Usuario entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Dados do usuário são obrigatórios");

        Usuario existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String novoLogin = dto.getLogin();
        if (novoLogin != null && !novoLogin.isBlank() && !novoLogin.equals(existente.getLogin())) {
            if (repository.existsByLogin(novoLogin)) {
                throw new RuntimeException("Login já está em uso.");
            }
            existente.setLogin(novoLogin);
        }

        if (dto.getNome() != null && !dto.getNome().isBlank()) {
            existente.setNome(dto.getNome());
        }

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            existente.setSenha(dto.getSenha());
        }

        return toDTO(repository.save(existente));
    }

    public void delete(Long id) {
        Usuario u = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        repository.delete(u);
    }

    private UsuarioDTO toDTO(Usuario entity) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setLogin(entity.getLogin());
        dto.setSenha(entity.getSenha());
        return dto;
    }

    private Usuario toEntity(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setLogin(dto.getLogin());
        entity.setSenha(dto.getSenha());
        return entity;
    }
}
