package com.bootcampdelloite.desafiospringboot.Validation;

import com.bootcampdelloite.desafiospringboot.Dto.UsuarioRequestDTO;
import com.bootcampdelloite.desafiospringboot.Exception.UsuarioException;
import org.springframework.stereotype.Component;

@Component
public class EmailValidation implements UsuarioValidation {

    @Override
    public void validar(UsuarioRequestDTO dto) {
        if (dto.email() == null || dto.email().isBlank()) {
            throw new UsuarioException("Email não pode ser vazio.");
        }
        if (!dto.email().contains("@gmail.com")) {
            throw new UsuarioException("Email inválido. Deve conter '@gmail.com'.");
        }
        if (dto.email().length() > 150) {
            throw new UsuarioException("Email deve ter no máximo 150 caracteres.");
        }
    }
}