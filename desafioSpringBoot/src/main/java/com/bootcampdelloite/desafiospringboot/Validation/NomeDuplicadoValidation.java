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

    @Override
    public void validar(UsuarioRequestDTO dto, Long idIgnorar) {
        if (dto.nome() == null || dto.nome().isBlank()) return;

        repository.findByNomeIgnoreCase(dto.nome()).ifPresent(u -> {
            if (!u.getId().equals(idIgnorar)) {
                throw new UsuarioException("Já existe um usuário com esse nome.");
            }
        });
    }
}