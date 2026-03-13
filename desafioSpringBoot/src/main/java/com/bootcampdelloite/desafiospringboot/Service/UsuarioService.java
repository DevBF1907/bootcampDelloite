package com.bootcampdelloite.desafiospringboot.Service;

import com.bootcampdelloite.desafiospringboot.Dto.UsuarioRequestDTO;
import com.bootcampdelloite.desafiospringboot.Dto.UsuarioResponseDTO;
import com.bootcampdelloite.desafiospringboot.Exception.UsuarioException;
import com.bootcampdelloite.desafiospringboot.Model.Usuario;
import com.bootcampdelloite.desafiospringboot.Repository.UsuarioRepository;
import com.bootcampdelloite.desafiospringboot.Validation.UsuarioValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final List<UsuarioValidation> validations;

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) {
        validations.forEach(v -> v.validar(dto));

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

        validations.forEach(v -> v.validar(dto));

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