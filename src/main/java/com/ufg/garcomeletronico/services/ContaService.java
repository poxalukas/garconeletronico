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

import java.math.BigDecimal;
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


    @Autowired
    private EntityDTOConverter converter;


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
//        conta.setValorTotal(calcularValorTotal(conta));

        entity = repository.save(entity);
        return toDTO(entity);
    }

    public ContaDTO update(Long id, ContaDTO dto) {

        Conta existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        existing.setNome(dto.getNome());

        if (dto.getMesa() != null) {
            existing.setMesa(converter.toEntity(dto.getMesa()));
        }

        if (dto.getPedidos() != null) {
            existing.setPedidos(converter.toPedidosEntity(dto.getPedidos()));
        }

        if (dto.getPedidos() != null) {
            existing.setPedidos(converter.toPedidosEntity(dto.getPedidos()));
        }

        existing = repository.save(existing);

        return toDTO(existing);
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
                .map(converter::toPedidoDTO)
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
                c.getMesa() != null ? converter.toMesaDTO(c.getMesa()) : null,
                c.getPedidos() != null ? converter.toPedidosDTO(c.getPedidos()) : List.of(),
                c.getPagamento() != null ? converter.toPagamentoDTO(c.getPagamento()) : null
        );
    }

    private Conta toEntity(ContaDTO dto) {
        if (dto == null) return null;

        Conta c = new Conta();
        c.setId(dto.getId());
        c.setNome(dto.getNome());
        return c;
    }
//    private BigDecimal calcularValorTotal(Conta conta) {
//        BigDecimal totalPedidos = BigDecimal.ZERO;
//        BigDecimal totalPagamentos = BigDecimal.ZERO;
//
//        if (conta.getPedidos() != null) {
//            totalPedidos = conta.getPedidos().stream()
//                    .map(p -> p.getValorTotal() != null ? p.getValorTotal() : BigDecimal.ZERO)
//                    .reduce(BigDecimal.ZERO, BigDecimal::add);
//        }
//
//        if (conta.getPagamento() != null && conta.getPagamento().getValorPago() != null) {
//            totalPagamentos = conta.getPagamento().getValorPago();
//        }
//
//        return totalPedidos.subtract(totalPagamentos);
//    }


    // Buscar todas as mesas
    public List<MesaDTO> buscarMesas() {
        return mesaRepository.findAll().stream()
                .map(EntityDTOConverter::toMesaDTO)
                .toList();
    }

    // Listar mesas atendidas por garçom
    public List<ContaDTO> listarMesasAtendidasGarcon(Long garcomId) {
        List<Conta> contas = repository.findByGarcomId(garcomId);
        return contas.stream()
                .map(EntityDTOConverter::toDTO)
                .toList();
    }

    // Buscar conta de um cliente
    public ContaDTO buscarContaPorCliente(Long clienteId) {
        Conta conta = repository.findByClienteId(clienteId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return EntityDTOConverter.toDTO(conta);
    }


}
