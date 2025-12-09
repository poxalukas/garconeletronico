package com.ufg.garcomeletronico.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GarcomDTO extends UsuarioDTO {

    public GarcomDTO(Long id, String nome) {
        super(id, nome, null, null);
    }

}
