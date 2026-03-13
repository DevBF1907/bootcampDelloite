package com.bootcampdelloite.desafiospringboot.Validation;

import com.bootcampdelloite.desafiospringboot.Dto.UsuarioRequestDTO;
import com.bootcampdelloite.desafiospringboot.Exception.UsuarioException;
import org.springframework.stereotype.Component;

@Component
public class CpfValidation implements UsuarioValidation {

    @Override
    public void validar(UsuarioRequestDTO dto) {
        if (dto.cpf() == null || dto.cpf().isBlank()) {
            throw new UsuarioException("CPF não pode ser vazio.");
        }

        String cpfNumeros = dto.cpf().replaceAll("[^0-9]", "");

        if (cpfNumeros.length() != 11) {
            throw new UsuarioException("CPF deve conter 11 dígitos.");
        }
        if (cpfNumeros.chars().distinct().count() == 1) {
            throw new UsuarioException("CPF inválido.");
        }
    }
}