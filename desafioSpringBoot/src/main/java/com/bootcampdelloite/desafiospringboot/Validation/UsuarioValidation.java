package com.bootcampdelloite.desafiospringboot.Validation;

import com.bootcampdelloite.desafiospringboot.Dto.UsuarioRequestDTO;

public interface UsuarioValidation {
    void validar(UsuarioRequestDTO dto);

    default void validar(UsuarioRequestDTO dto, Long idIgnorar) {
        validar(dto);
    }
}