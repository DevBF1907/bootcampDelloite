package com.bootcampdelloite.desafiospringboot.Dto;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String senha) {
}
