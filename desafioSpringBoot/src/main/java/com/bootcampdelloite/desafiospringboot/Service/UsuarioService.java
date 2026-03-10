package com.bootcampdelloite.desafiospringboot.Service;

import com.bootcampdelloite.desafiospringboot.Dto.UsuarioRequestDTO;
import com.bootcampdelloite.desafiospringboot.Dto.UsuarioResponseDTO;
import com.bootcampdelloite.desafiospringboot.Exception.UsuarioException;
import com.bootcampdelloite.desafiospringboot.Model.Usuario;
import com.bootcampdelloite.desafiospringboot.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) {
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new UsuarioException("Nome não pode ser vazio.");
        }
        if (repository.existsByNomeIgnoreCase(dto.nome())) {
            throw new UsuarioException("Já existe um usuário com esse nome.");
        }
        if (dto.email() == null || dto.email().isBlank()) {
            throw new UsuarioException("Email não pode ser vazio.");
        }
        if (!dto.email().contains("@gmail.com")) {
            throw new UsuarioException("Email inválido. Deve conter '@gmail.com'.");
        }
        if (repository.existsByEmailIgnoreCase(dto.email())) {
            throw new UsuarioException("Esse email já está cadastrado.");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(dto.senha())
                .build();
        Usuario salvo = repository.save(usuario);
        return new UsuarioResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
    }

    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = repository.findAll();
        if (usuarios.isEmpty()) {
            throw new UsuarioException("Nenhum usuário cadastrado.");
        }
        return usuarios.stream()
                .map(u -> new UsuarioResponseDTO(u.getId(), u.getNome(), u.getEmail()))
                .toList();
    }

    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {
        Usuario u = repository.findById(id)
                .orElseThrow(() -> new UsuarioException("Usuário com ID " + id + " não encontrado."));
        return new UsuarioResponseDTO(u.getId(), u.getNome(), u.getEmail());
    }

    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioException("Usuário com ID " + id + " não encontrado."));
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new UsuarioException("Nome não pode ser vazio.");
        }
        usuario.setNome(dto.nome());
        Usuario salvo = repository.save(usuario);
        return new UsuarioResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
    }

    public void deletarUsuario(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioException("Usuário com ID " + id + " não encontrado."));
        repository.delete(usuario);
    }
}