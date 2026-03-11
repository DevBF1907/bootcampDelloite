package com.bootcampdelloite.desafiospringboot.Service;

import com.bootcampdelloite.desafiospringboot.Dto.UsuarioRequestDTO;
import com.bootcampdelloite.desafiospringboot.Dto.UsuarioResponseDTO;
import com.bootcampdelloite.desafiospringboot.Exception.UsuarioException;
import com.bootcampdelloite.desafiospringboot.Model.Usuario;
import com.bootcampdelloite.desafiospringboot.Repository.UsuarioRepository;
import com.bootcampdelloite.desafiospringboot.Service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do UsuarioService")
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Test
    @DisplayName("Deve criar usuário com sucesso")
    void deveCriarUsuarioComSucesso() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno", "brenno@gmail.com", "123456");

        Usuario usuarioSalvo = Usuario.builder()
                .id(1L)
                .nome("Brenno")
                .email("brenno@gmail.com")
                .senha("123456")
                .build();

        when(repository.existsByNomeIgnoreCase("Brenno")).thenReturn(false);
        when(repository.existsByEmailIgnoreCase("brenno@gmail.com")).thenReturn(false);
        when(repository.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        UsuarioResponseDTO response = service.criarUsuario(dto);

        assertEquals("Brenno", response.nome());
        assertEquals("brenno@gmail.com", response.email());
        assertEquals(1L, response.id());
        verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome for vazio")
    void deveLancarExcecaoQuandoNomeVazio() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("", "brenno@gmail.com", "123456");

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Nome não pode ser vazio.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome for nulo")
    void deveLancarExcecaoQuandoNomeNulo() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(null, "brenno@gmail.com", "123456");

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Nome não pode ser vazio.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome já existir")
    void deveLancarExcecaoQuandoNomeDuplicado() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno", "brenno@gmail.com", "123456");

        when(repository.existsByNomeIgnoreCase("Brenno")).thenReturn(true);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Já existe um usuário com esse nome.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando email for vazio")
    void deveLancarExcecaoQuandoEmailVazio() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno", "", "123456");

        when(repository.existsByNomeIgnoreCase("Brenno")).thenReturn(false);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Email não pode ser vazio.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando email for inválido")
    void deveLancarExcecaoQuandoEmailInvalido() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno", "brenno@hotmail.com", "123456");

        when(repository.existsByNomeIgnoreCase("Brenno")).thenReturn(false);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Email inválido. Deve conter '@gmail.com'.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando email já estiver cadastrado")
    void deveLancarExcecaoQuandoEmailDuplicado() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno", "brenno@gmail.com", "123456");

        when(repository.existsByNomeIgnoreCase("Brenno")).thenReturn(false);
        when(repository.existsByEmailIgnoreCase("brenno@gmail.com")).thenReturn(true);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Esse email já está cadastrado.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve listar usuários com sucesso")
    void deveListarUsuariosComSucesso() {
        Usuario usuario = Usuario.builder()
                .id(1L).nome("Brenno").email("brenno@gmail.com").senha("123456")
                .build();

        when(repository.findAll()).thenReturn(List.of(usuario));

        List<UsuarioResponseDTO> response = service.listarUsuarios();

        assertEquals(1, response.size());
        assertEquals("Brenno", response.get(0).nome());
    }

    @Test
    @DisplayName("Deve lançar exceção quando não houver usuários cadastrados")
    void deveLancarExcecaoQuandoListaVazia() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.listarUsuarios());
        assertEquals("Nenhum usuário cadastrado.", ex.getMessage());
    }

    @Test
    @DisplayName("Deve buscar usuário por ID com sucesso")
    void deveBuscarUsuarioPorIdComSucesso() {
        Usuario usuario = Usuario.builder()
                .id(1L).nome("Brenno").email("brenno@gmail.com").senha("123456")
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        UsuarioResponseDTO response = service.buscarUsuarioPorId(1L);

        assertEquals(1L, response.id());
        assertEquals("Brenno", response.nome());
    }

    @Test
    @DisplayName("Deve lançar exceção quando ID não for encontrado")
    void deveLancarExcecaoQuandoIdNaoEncontrado() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.buscarUsuarioPorId(99L));
        assertEquals("Usuário com ID 99 não encontrado.", ex.getMessage());
    }


    @Test
    @DisplayName("Deve atualizar usuário com sucesso")
    void deveAtualizarUsuarioComSucesso() {
        Usuario usuario = Usuario.builder()
                .id(1L).nome("Brenno").email("brenno@gmail.com").senha("123456")
                .build();

        Usuario atualizado = Usuario.builder()
                .id(1L).nome("Brenno Atualizado").email("brenno@gmail.com").senha("123456")
                .build();

        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno Atualizado", "brenno@gmail.com", "123456");

        when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        when(repository.save(any(Usuario.class))).thenReturn(atualizado);

        UsuarioResponseDTO response = service.atualizarUsuario(1L, dto);

        assertEquals("Brenno Atualizado", response.nome());
        verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar com nome vazio")
    void deveLancarExcecaoAoAtualizarComNomeVazio() {
        Usuario usuario = Usuario.builder()
                .id(1L).nome("Brenno").email("brenno@gmail.com").senha("123456")
                .build();

        UsuarioRequestDTO dto = new UsuarioRequestDTO("", "brenno@gmail.com", "123456");

        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.atualizarUsuario(1L, dto));
        assertEquals("Nome não pode ser vazio.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve deletar usuário com sucesso")
    void deveDeletarUsuarioComSucesso() {
        Usuario usuario = Usuario.builder()
                .id(1L).nome("Brenno").email("brenno@gmail.com").senha("123456")
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        assertDoesNotThrow(() -> service.deletarUsuario(1L));
        verify(repository, times(1)).delete(usuario);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar ID inexistente")
    void deveLancarExcecaoAoDeletarIdInexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.deletarUsuario(99L));
        assertEquals("Usuário com ID 99 não encontrado.", ex.getMessage());
        verify(repository, never()).delete(any());
    }
}