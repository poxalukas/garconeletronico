package com.ufg.garcomeletronico.converters;

import com.ufg.garcomeletronico.dto.*;
import com.ufg.garcomeletronico.entities.*;
import com.ufg.garcomeletronico.repositories.ClienteRepository;
import com.ufg.garcomeletronico.repositories.ContaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntityDTOConverter {

    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;

    public EntityDTOConverter(ClienteRepository clienteRepository,
                              ContaRepository contaRepository) {
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
    }

    // ====================
    // CONVERSÃO PARA DTOs
    // ====================

    public ContaDTO toDTO(Conta c) {
        if (c == null) return null;
        return new ContaDTO(
                c.getId(),
                c.getNome(),
                toMesaDTO(c.getMesa()),
                toPedidosDTO(c.getPedidos()),
                toPagamentoDTO(c.getPagamento())
        );
    }

    public MesaDTO toMesaDTO(Mesa m) {
        if (m == null) return null;
        return new MesaDTO(m.getId(), m.getNumero(), m.isDisponivel(), null);
    }

    public PedidoDTO toPedidoDTO(Pedido p) {
        if (p == null) return null;
        return new PedidoDTO(
                p.getId(),
                p.getNumero(),
                p.getHoraPedido(),
                p.getHoraEntrega(),
                toClienteDTO(p.getCliente()),
                toItensPedidoDTO(p.getItens()),
                null
        );
    }

    public List<PedidoDTO> toPedidosDTO(List<Pedido> lista) {
        if (lista == null) return null;
        return lista.stream().map(this::toPedidoDTO).toList();
    }

    public ClienteDTO toClienteDTO(Cliente c) {
        if (c == null) return null;
        return new ClienteDTO(c.getId(), c.getNome(), c.getHoraChegada(), c.getHoraSaida());
    }

    public ItemPedidoDTO toItemPedidoDTO(ItemPedido entity) {
        if (entity == null) return null;
        return new ItemPedidoDTO(entity.getId(), entity.getQuantidade(), toItemCardapioDTO(entity.getItemCardapio()));
    }

    public List<ItemPedidoDTO> toItensPedidoDTO(List<ItemPedido> lista) {
        if (lista == null) return null;
        return lista.stream().map(this::toItemPedidoDTO).toList();
    }

    public ItemCardapioDTO toItemCardapioDTO(ItemCardapio entity) {
        if (entity == null) return null;
        return new ItemCardapioDTO(entity.getId(), entity.getNome(), entity.getIngredientes(),
                entity.getPreco(), entity.isDisponivelCozinha(), toCategoriaDTO(entity.getCategoria()));
    }

    public CategoriaDTO toCategoriaDTO(Categoria entity) {
        if (entity == null) return null;
        return new CategoriaDTO(entity.getId(), entity.getNome());
    }

    public PagamentoDTO toPagamentoDTO(Pagamento p) {
        if (p == null) return null;
        return new PagamentoDTO(p.getId(), p.getTipo(), p.getNumeroTransacao(), p.getNumeroCheque());
    }

    // ====================
    // CONVERSÃO PARA ENTIDADES
    // ====================

    public Pedido toEntity(PedidoDTO dto) {
        if (dto == null) return null;

        Pedido pedido = new Pedido();
        pedido.setNumero(dto.getNumero());
        pedido.setHoraPedido(dto.getHoraPedido());
        pedido.setHoraEntrega(dto.getHoraEntrega());

        Cliente cliente = clienteRepository.findById(dto.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        pedido.setCliente(cliente);

        Conta conta = contaRepository.findById(dto.getConta().getId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        pedido.setConta(conta);

        if (dto.getItens() != null) {
            List<ItemPedido> itens = dto.getItens().stream().map(itemDto -> {
                ItemPedido item = new ItemPedido();
                item.setQuantidade(itemDto.getQuantidade());

                ItemCardapio ic = new ItemCardapio();
                ic.setId(itemDto.getItemCardapio().getId());
                item.setItemCardapio(ic);

                return item;
            }).toList();
            pedido.setItens(itens);
        }

        return pedido;
    }

    public Cliente toCliente(ClienteDTO dto) {
        if (dto == null) return null;
        Cliente c = new Cliente();
        c.setId(dto.getId());
        c.setNome(dto.getNome());
        c.setHoraChegada(dto.getHoraChegada());
        c.setHoraSaida(dto.getHoraSaida());
        return c;
    }

    public Conta toEntity(ContaDTO dto) {
        if (dto == null) return null;
        Conta c = new Conta();
        c.setId(dto.getId());
        c.setNome(dto.getNome());
        return c;
    }

    public Garcom toEntity(GarcomDTO dto) {
        if (dto == null) return null;
        Garcom g = new Garcom();
        g.setId(dto.getId());
        g.setNome(dto.getNome());
        return g;
    }

    public ItemPedido toItemPedido(ItemPedidoDTO dto) {
        if (dto == null) return null;
        ItemPedido i = new ItemPedido();
        i.setQuantidade(dto.getQuantidade());
        i.setItemCardapio(toItemCardapio(dto.getItemCardapio()));
        return i;
    }

    public ItemCardapio toItemCardapio(ItemCardapioDTO dto) {
        if (dto == null) return null;
        ItemCardapio entity = new ItemCardapio();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setIngredientes(dto.getIngredientes());
        entity.setPreco(dto.getPreco());
        entity.setDisponivelCozinha(dto.isDisponivelCozinha());
        entity.setCategoria(toCategoria(dto.getCategoria()));
        return entity;
    }

    public Categoria toCategoria(CategoriaDTO dto) {
        if (dto == null) return null;
        Categoria c = new Categoria();
        c.setId(dto.getId());
        c.setNome(dto.getNome());
        return c;
    }

    public Pagamento toEntity(PagamentoDTO dto) {
        if (dto == null) return null;
        Pagamento p = new Pagamento();
        p.setId(dto.getId());
        p.setTipo(dto.getTipo());
        p.setNumeroTransacao(dto.getNumeroTransacao());
        p.setNumeroCheque(dto.getNumeroCheque());
        return p;
    }

    public Mesa toEntity(MesaDTO dto) {
        if (dto == null) return null;
        Mesa m = new Mesa();
        m.setId(dto.getId());
        m.setNumero(dto.getNumero());
        m.setDisponivel(dto.isDisponivel());
        return m;
    }

    public List<Pedido> toPedidosEntity(List<PedidoDTO> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }
}
