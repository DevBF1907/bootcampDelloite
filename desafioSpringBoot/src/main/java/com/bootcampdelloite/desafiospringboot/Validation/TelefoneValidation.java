package com.bootcampdelloite.desafiospringboot.Validation;

import com.bootcampdelloite.desafiospringboot.Dto.UsuarioRequestDTO;
import com.bootcampdelloite.desafiospringboot.Exception.UsuarioException;
import org.springframework.stereotype.Component;

@Component
public class TelefoneValidation implements UsuarioValidation {

    @Override
    public void validar(UsuarioRequestDTO dto) {
        if (dto.telefone() == null || dto.telefone().isBlank()) {
            throw new UsuarioException("Telefone não pode ser vazio.");
        }

        String telefoneNumeros = dto.telefone().replaceAll("[^0-9]", "");

        if (telefoneNumeros.length() < 10 || telefoneNumeros.length() > 11) {
            throw new UsuarioException("Telefone deve conter 10 ou 11 dígitos.");
        }
    }
}