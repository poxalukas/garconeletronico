package com.ufg.garcomeletronico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CozinhaDTO extends UsuarioDTO {
    public CozinhaDTO(Long id, String nome) {
        super(id, nome, null, null);
    }
}
