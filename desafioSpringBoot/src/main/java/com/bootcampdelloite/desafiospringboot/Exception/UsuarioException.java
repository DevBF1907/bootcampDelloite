package com.bootcampdelloite.desafiospringboot.Exception;

public class UsuarioException extends RuntimeException {

    public UsuarioException(String mensagem) {
        super(mensagem);
    }
}