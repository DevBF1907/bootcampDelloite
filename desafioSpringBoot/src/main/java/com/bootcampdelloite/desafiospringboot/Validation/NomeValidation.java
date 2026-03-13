package com.bootcampdelloite.desafiospringboot.Validation;

import com.bootcampdelloite.desafiospringboot.Dto.UsuarioRequestDTO;
import com.bootcampdelloite.desafiospringboot.Exception.UsuarioException;
import org.springframework.stereotype.Component;

@Component
public class NomeValidation implements UsuarioValidation {

    @Override
    public void validar(UsuarioRequestDTO dto) {
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new UsuarioException("Nome não pode ser vazio.");
        }
        if (dto.nome().length() < 3) {
            throw new UsuarioException("Nome deve ter no mínimo 3 caracteres.");
        }
        if (dto.nome().length() > 100) {
            throw new UsuarioException("Nome deve ter no máximo 100 caracteres.");
        }
        if (!dto.nome().matches("^[a-zA-ZÀ-ÿ\\s]+$")) {
            throw new UsuarioException("Nome não pode conter números ou caracteres especiais.");
        }
    }
}