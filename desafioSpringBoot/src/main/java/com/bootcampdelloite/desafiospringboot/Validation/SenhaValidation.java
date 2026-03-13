package com.bootcampdelloite.desafiospringboot.Validation;

import com.bootcampdelloite.desafiospringboot.Dto.UsuarioRequestDTO;
import com.bootcampdelloite.desafiospringboot.Exception.UsuarioException;
import org.springframework.stereotype.Component;

@Component
public class SenhaValidation implements UsuarioValidation {

    @Override
    public void validar(UsuarioRequestDTO dto) {
        if (dto.senha() == null || dto.senha().isBlank()) {
            throw new UsuarioException("Senha não pode ser vazia.");
        }
        if (dto.senha().length() < 6) {
            throw new UsuarioException("Senha deve ter no mínimo 6 caracteres.");
        }
        if (dto.senha().length() > 50) {
            throw new UsuarioException("Senha deve ter no máximo 50 caracteres.");
        }
    }
}