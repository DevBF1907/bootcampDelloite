package com.bootcampdelloite.desafiospringboot.Validation;

import com.bootcampdelloite.desafiospringboot.Dto.UsuarioRequestDTO;
import com.bootcampdelloite.desafiospringboot.Exception.UsuarioException;
import com.bootcampdelloite.desafiospringboot.Repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class EmailDuplicadoValidation implements UsuarioValidation {

    private final UsuarioRepository repository;

    public EmailDuplicadoValidation(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validar(UsuarioRequestDTO dto) {
        if (dto.email() != null && repository.existsByEmailIgnoreCase(dto.email())) {
            throw new UsuarioException("Esse email já está cadastrado.");
        }
    }
}