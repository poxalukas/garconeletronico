package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.dto.CategoriaDTO;
import com.ufg.garcomeletronico.dto.ItemCardapioDTO;
import com.ufg.garcomeletronico.entities.Categoria;
import com.ufg.garcomeletronico.entities.ItemCardapio;
import com.ufg.garcomeletronico.repositories.ItemCardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCardapioService {

    @Autowired
    private ItemCardapioRepository repository;

    public List<ItemCardapioDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ItemCardapioDTO findById(Long id) {
        return toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado")));
    }

    public ItemCardapioDTO create(ItemCardapioDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public ItemCardapioDTO update(Long id, ItemCardapioDTO dto) {
        ItemCardapio entity = toEntity(dto);
        entity.setId(id);
        return toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado")));
    }


    private ItemCardapioDTO toDTO(ItemCardapio item) {
        CategoriaDTO categoriaDTO = null;

        if (item.getCategoria() != null) {
            categoriaDTO = new CategoriaDTO(
                    item.getCategoria().getId(),
                    item.getCategoria().getNome()
            );
        }

        return new ItemCardapioDTO(
                item.getId(),
                item.getNome(),
                item.getIngredientes(),
                item.getPreco(),
                item.isDisponivelCozinha(),
                categoriaDTO
        );
    }

    private ItemCardapio toEntity(ItemCardapioDTO dto) {
        ItemCardapio item = new ItemCardapio();
        item.setId(dto.getId());
        item.setNome(dto.getNome());
        item.setIngredientes(dto.getIngredientes());
        item.setPreco(dto.getPreco());
        item.setDisponivelCozinha(dto.isDisponivelCozinha());

        if (dto.getCategoria() != null) {
            Categoria cat = new Categoria();
            cat.setId(dto.getCategoria().getId());
            cat.setNome(dto.getCategoria().getNome());
            item.setCategoria(cat);
        }

        return item;
    }

}
