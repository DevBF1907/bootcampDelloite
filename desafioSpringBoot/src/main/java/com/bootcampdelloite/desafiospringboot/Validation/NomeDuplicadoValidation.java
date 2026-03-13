package com.bootcampdelloite.desafiospringboot.Validation;

import com.bootcampdelloite.desafiospringboot.Dto.UsuarioRequestDTO;
import com.bootcampdelloite.desafiospringboot.Exception.UsuarioException;
import com.bootcampdelloite.desafiospringboot.Repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class NomeDuplicadoValidation implements UsuarioValidation {

    private final UsuarioRepository repository;

    public NomeDuplicadoValidation(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validar(UsuarioRequestDTO dto) {
        if (dto.nome() != null && repository.existsByNomeIgnoreCase(dto.nome())) {
            throw new UsuarioException("Já existe um usuário com esse nome.");
        }
    }
}
