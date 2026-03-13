package com.bootcampdelloite.desafiospringboot.Dto;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone
) {}