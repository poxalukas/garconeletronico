package com.ufg.garcomeletronico.dto;

import com.ufg.garcomeletronico.entities.Caixa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CaixaDTO extends UsuarioDTO {

    public static CaixaDTO toDTO(Caixa caixa) {
        CaixaDTO dto = new CaixaDTO();
        dto.setId(caixa.getId());
        dto.setNome(caixa.getNome());
        dto.setLogin(caixa.getLogin());
        dto.setSenha(null);
        return dto;
    }
}
