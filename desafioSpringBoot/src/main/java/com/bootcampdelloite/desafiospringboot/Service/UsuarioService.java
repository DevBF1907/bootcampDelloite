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
                .cpf(dto.cpf())
                .telefone(dto.telefone())
                .build();

        Usuario salvo = repository.save(usuario);
        return toDTO(salvo);
    }

    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = repository.findAll();
        if (usuarios.isEmpty()) {
            throw new UsuarioException("Nenhum usuário cadastrado.");
        }
        return usuarios.stream()
                .map(this::toDTO)
                .toList();
    }

    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {
        Usuario u = repository.findById(id)
                .orElseThrow(() -> new UsuarioException("Usuário com ID " + id + " não encontrado."));
        return toDTO(u);
    }

    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioException("Usuário com ID " + id + " não encontrado."));

        validations.forEach(v -> v.validar(dto));

        usuario.setNome(dto.nome());
        usuario.setCpf(dto.cpf());
        usuario.setTelefone(dto.telefone());
        Usuario salvo = repository.save(usuario);
        return toDTO(salvo);
    }

    public void deletarUsuario(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioException("Usuário com ID " + id + " não encontrado."));
        repository.delete(usuario);
    }

    private UsuarioResponseDTO toDTO(Usuario u) {
        return new UsuarioResponseDTO(u.getId(), u.getNome(), u.getEmail(), u.getCpf(), u.getTelefone());
    }
}