package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.dto.CardapioDTO;
import com.ufg.garcomeletronico.dto.ItemCardapioDTO;
import com.ufg.garcomeletronico.entities.Cardapio;
import com.ufg.garcomeletronico.entities.ItemCardapio;
import com.ufg.garcomeletronico.repositories.CardapioRepository;
import com.ufg.garcomeletronico.repositories.ItemCardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;

@Service
public class CardapioService {

    @Autowired
    private CardapioRepository repository;

    @Autowired
    private ItemCardapioRepository itemRepository;

    public List<CardapioDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CardapioDTO findById(Long id) {
        return toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cardápio não encontrado")));
    }

    public CardapioDTO create(CardapioDTO dto) {
        Cardapio c = new Cardapio();
        c.setItens(dto.getItens()
                .stream()
                .map(i -> itemRepository.findById(i.getId())
                        .orElseThrow(() -> new RuntimeException("Item não encontrado: " + i.getId())))
                .collect(Collectors.toList())
        );
        c = repository.save(c);
        return toDTO(c);
    }

    public CardapioDTO update(Long id, CardapioDTO dto) {
        Cardapio c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cardápio não encontrado"));

        List<ItemCardapio> itens = dto.getItens()
                .stream()
                .map(i -> itemRepository.findById(i.getId())
                        .orElseThrow(() -> new RuntimeException("Item não encontrado: " + i.getId())))
                .collect(Collectors.toList());

        c.setItens(itens);
        c = repository.save(c);
        return toDTO(c);
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cardápio não encontrado")));
    }

    private CardapioDTO toDTO(Cardapio c) {
        CardapioDTO dto = new CardapioDTO();
        dto.setId(c.getId());
        dto.setItens(
                c.getItens().stream().map(item -> {
                    ItemCardapioDTO i = new ItemCardapioDTO();
                    i.setId(item.getId());
                    i.setNome(item.getNome());
                    i.setPreco(item.getPreco());
                    return i;
                }).collect(Collectors.toList())
        );
        return dto;
    }
}
