package com.ufg.garcomeletronico.services;

import com.ufg.garcomeletronico.converters.EntityDTOConverter;
import com.ufg.garcomeletronico.dto.ContaDTO;
import com.ufg.garcomeletronico.dto.MesaDTO;
import com.ufg.garcomeletronico.dto.PagamentoDTO;
import com.ufg.garcomeletronico.dto.PedidoDTO;
import com.ufg.garcomeletronico.entities.*;
import com.ufg.garcomeletronico.repositories.ContaRepository;
import com.ufg.garcomeletronico.repositories.MesaRepository;
import com.ufg.garcomeletronico.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.ufg.garcomeletronico.converters.EntityDTOConverter.*;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;


    // -------------------- CRUD --------------------

    public List<ContaDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ContaDTO findById(Long id) {
        Conta conta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return toDTO(conta);
    }

    public ContaDTO create(ContaDTO dto) {
        Conta entity = toEntity(dto);
        entity = repository.save(entity);
        return toDTO(entity);
    }

    public ContaDTO update(Long id, ContaDTO dto) {
        Conta existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        Conta updated = toEntity(dto);
        updated.setId(id);

        return toDTO(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


    // -------------------- PERSONALIZADOS --------------------

    public List<ContaDTO> findByMesa(Long mesaId) {
        return repository.findByMesaId(mesaId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<PedidoDTO> getPedidos(Long contaId) {
        Conta conta = repository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        return conta.getPedidos()
                .stream()
                .map(EntityDTOConverter::toPedidoDTO)
                .toList();
    }

    public ContaDTO abrirConta(Long mesaId, ContaDTO dto) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        if (!mesa.isDisponivel()) {
            throw new RuntimeException("Mesa já está ocupada");
        }

        mesa.setDisponivel(false);
        mesaRepository.save(mesa);

        Conta conta = toEntity(dto);
        conta.setMesa(mesa);

        return toDTO(repository.save(conta));
    }

    public ContaDTO fecharConta(Long id) {
        Conta conta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        if (conta.getMesa() != null) {
            conta.getMesa().setDisponivel(true);
            mesaRepository.save(conta.getMesa());
        }

        if (conta.getPagamento() != null) {
            conta.getPagamento().setHoraPagamento(LocalDateTime.now());
            pagamentoRepository.save(conta.getPagamento());
        }

        return toDTO(repository.save(conta));
    }

    public ContaDTO registrarPagamento(Long id, PagamentoDTO dto) {
        Conta conta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        Pagamento pagamento = new Pagamento();
        pagamento.setTipo(dto.getTipo());
        pagamento.setNumeroTransacao(dto.getNumeroTransacao());
        pagamento.setNumeroCheque(dto.getNumeroCheque());
        pagamento.setHoraPagamento(LocalDateTime.now());

        pagamento = pagamentoRepository.save(pagamento);

        conta.setPagamento(pagamento);
        repository.save(conta);

        return toDTO(conta);
    }

    private ContaDTO toDTO(Conta c) {
        if (c == null) return null;

        return new ContaDTO(
                c.getId(),
                c.getNome(),
                toMesaDTO(c.getMesa()),
                toPedidosDTO(c.getPedidos()),
                toPagamentoDTO(c.getPagamento())
        );
    }

    private Conta toEntity(ContaDTO dto) {
        if (dto == null) return null;

        Conta c = new Conta();
        c.setId(dto.getId());
        c.setNome(dto.getNome());
        return c;
    }

}
