package com.bootcampdelloite.desafiospringboot.Service.Service;

import com.bootcampdelloite.desafiospringboot.Dto.UsuarioRequestDTO;
import com.bootcampdelloite.desafiospringboot.Dto.UsuarioResponseDTO;
import com.bootcampdelloite.desafiospringboot.Exception.UsuarioException;
import com.bootcampdelloite.desafiospringboot.Model.Usuario;
import com.bootcampdelloite.desafiospringboot.Repository.UsuarioRepository;
import com.bootcampdelloite.desafiospringboot.Service.UsuarioService;
import com.bootcampdelloite.desafiospringboot.Validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    private UsuarioService service;

    private final UsuarioRequestDTO dtoValido = new UsuarioRequestDTO(
            "Brenno Silva", "brenno@gmail.com", "123456", "123.456.789-09", "11987654321"
    );

    @BeforeEach
    void setUp() {
        List<UsuarioValidation> validations = List.of(
                new NomeValidation(),
                new NomeDuplicadoValidation(repository),
                new EmailValidation(),
                new EmailDuplicadoValidation(repository),
                new SenhaValidation(),
                new CpfValidation(),
                new TelefoneValidation()
        );
        service = new UsuarioService(repository, validations);
    }

    private Usuario buildUsuario() {
        return Usuario.builder()
                .id(1L).nome("Brenno Silva").email("brenno@gmail.com")
                .senha("123456").cpf("12345678909").telefone("11987654321")
                .build();
    }


    @Test
    @DisplayName("Deve criar usuário com sucesso")
    void deveCriarUsuarioComSucesso() {
        when(repository.existsByNomeIgnoreCase("Brenno Silva")).thenReturn(false);
        when(repository.existsByEmailIgnoreCase("brenno@gmail.com")).thenReturn(false);
        when(repository.save(any(Usuario.class))).thenReturn(buildUsuario());

        UsuarioResponseDTO response = service.criarUsuario(dtoValido);

        assertEquals(1L, response.id());
        assertEquals("Brenno Silva", response.nome());
        assertEquals("brenno@gmail.com", response.email());
        verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome for vazio")
    void deveLancarExcecaoQuandoNomeVazio() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("", "brenno@gmail.com", "123456", "12345678909", "11987654321");

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Nome não pode ser vazio.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome for nulo")
    void deveLancarExcecaoQuandoNomeNulo() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(null, "brenno@gmail.com", "123456", "12345678909", "11987654321");

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Nome não pode ser vazio.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome tiver menos de 3 caracteres")
    void deveLancarExcecaoQuandoNomeMuitoCurto() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Ab", "brenno@gmail.com", "123456", "12345678909", "11987654321");

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Nome deve ter no mínimo 3 caracteres.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome tiver mais de 100 caracteres")
    void deveLancarExcecaoQuandoNomeMuitoLongo() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("A".repeat(101), "brenno@gmail.com", "123456", "12345678909", "11987654321");

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Nome deve ter no máximo 100 caracteres.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome contiver caracteres especiais")
    void deveLancarExcecaoQuandoNomeComCaracteresEspeciais() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenn0@", "brenno@gmail.com", "123456", "12345678909", "11987654321");

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Nome não pode conter números ou caracteres especiais.", ex.getMessage());
        verify(repository, never()).save(any());
    }


    @Test
    @DisplayName("Deve lançar exceção quando nome já existir no banco")
    void deveLancarExcecaoQuandoNomeDuplicado() {
        when(repository.existsByNomeIgnoreCase("Brenno Silva")).thenReturn(true);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dtoValido));
        assertEquals("Já existe um usuário com esse nome.", ex.getMessage());
        verify(repository, never()).save(any());
    }


    @Test
    @DisplayName("Deve lançar exceção quando email for vazio")
    void deveLancarExcecaoQuandoEmailVazio() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno Silva", "", "123456", "12345678909", "11987654321");

        when(repository.existsByNomeIgnoreCase("Brenno Silva")).thenReturn(false);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Email não pode ser vazio.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando email for inválido")
    void deveLancarExcecaoQuandoEmailInvalido() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno Silva", "brenno@hotmail.com", "123456", "12345678909", "11987654321");

        when(repository.existsByNomeIgnoreCase("Brenno Silva")).thenReturn(false);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Email inválido. Deve conter '@gmail.com'.", ex.getMessage());
        verify(repository, never()).save(any());
    }


    @Test
    @DisplayName("Deve lançar exceção quando email já estiver cadastrado")
    void deveLancarExcecaoQuandoEmailDuplicado() {
        when(repository.existsByNomeIgnoreCase("Brenno Silva")).thenReturn(false);
        when(repository.existsByEmailIgnoreCase("brenno@gmail.com")).thenReturn(true);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dtoValido));
        assertEquals("Esse email já está cadastrado.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando senha for vazia")
    void deveLancarExcecaoQuandoSenhaVazia() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno Silva", "brenno@gmail.com", "", "12345678909", "11987654321");

        when(repository.existsByNomeIgnoreCase("Brenno Silva")).thenReturn(false);
        when(repository.existsByEmailIgnoreCase("brenno@gmail.com")).thenReturn(false);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Senha não pode ser vazia.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando senha tiver menos de 6 caracteres")
    void deveLancarExcecaoQuandoSenhaMuitoCurta() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno Silva", "brenno@gmail.com", "123", "12345678909", "11987654321");

        when(repository.existsByNomeIgnoreCase("Brenno Silva")).thenReturn(false);
        when(repository.existsByEmailIgnoreCase("brenno@gmail.com")).thenReturn(false);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Senha deve ter no mínimo 6 caracteres.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando senha tiver mais de 50 caracteres")
    void deveLancarExcecaoQuandoSenhaMuitoLonga() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno Silva", "brenno@gmail.com", "A".repeat(51), "12345678909", "11987654321");

        when(repository.existsByNomeIgnoreCase("Brenno Silva")).thenReturn(false);
        when(repository.existsByEmailIgnoreCase("brenno@gmail.com")).thenReturn(false);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Senha deve ter no máximo 50 caracteres.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CPF for vazio")
    void deveLancarExcecaoQuandoCpfVazio() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno Silva", "brenno@gmail.com", "123456", "", "11987654321");

        when(repository.existsByNomeIgnoreCase("Brenno Silva")).thenReturn(false);
        when(repository.existsByEmailIgnoreCase("brenno@gmail.com")).thenReturn(false);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("CPF não pode ser vazio.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CPF tiver menos de 11 dígitos")
    void deveLancarExcecaoQuandoCpfInvalido() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno Silva", "brenno@gmail.com", "123456", "1234567", "11987654321");

        when(repository.existsByNomeIgnoreCase("Brenno Silva")).thenReturn(false);
        when(repository.existsByEmailIgnoreCase("brenno@gmail.com")).thenReturn(false);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("CPF deve conter 11 dígitos.", ex.getMessage());
        verify(repository, never()).save(any());
    }


    @Test
    @DisplayName("Deve lançar exceção quando telefone for vazio")
    void deveLancarExcecaoQuandoTelefoneVazio() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno Silva", "brenno@gmail.com", "123456", "12345678909", "");

        when(repository.existsByNomeIgnoreCase("Brenno Silva")).thenReturn(false);
        when(repository.existsByEmailIgnoreCase("brenno@gmail.com")).thenReturn(false);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Telefone não pode ser vazio.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando telefone tiver menos de 10 dígitos")
    void deveLancarExcecaoQuandoTelefoneInvalido() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno Silva", "brenno@gmail.com", "123456", "12345678909", "123456");

        when(repository.existsByNomeIgnoreCase("Brenno Silva")).thenReturn(false);
        when(repository.existsByEmailIgnoreCase("brenno@gmail.com")).thenReturn(false);

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.criarUsuario(dto));
        assertEquals("Telefone deve conter 10 ou 11 dígitos.", ex.getMessage());
        verify(repository, never()).save(any());
    }


    @Test
    @DisplayName("Deve listar usuários com sucesso")
    void deveListarUsuariosComSucesso() {
        when(repository.findAll()).thenReturn(List.of(buildUsuario()));

        List<UsuarioResponseDTO> response = service.listarUsuarios();

        assertEquals(1, response.size());
        assertEquals("Brenno Silva", response.get(0).nome());
        assertEquals("brenno@gmail.com", response.get(0).email());
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
        when(repository.findById(1L)).thenReturn(Optional.of(buildUsuario()));

        UsuarioResponseDTO response = service.buscarUsuarioPorId(1L);

        assertEquals(1L, response.id());
        assertEquals("Brenno Silva", response.nome());
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
        Usuario atualizado = Usuario.builder()
                .id(1L).nome("Brenno Atualizado").email("brenno@gmail.com")
                .senha("123456").cpf("12345678909").telefone("11987654321")
                .build();

        UsuarioRequestDTO dto = new UsuarioRequestDTO("Brenno Atualizado", "brenno@gmail.com", "123456", "12345678909", "11987654321");

        when(repository.findById(1L)).thenReturn(Optional.of(buildUsuario()));
        when(repository.existsByNomeIgnoreCase("Brenno Atualizado")).thenReturn(false);
        when(repository.existsByEmailIgnoreCase("brenno@gmail.com")).thenReturn(false);
        when(repository.save(any(Usuario.class))).thenReturn(atualizado);

        UsuarioResponseDTO response = service.atualizarUsuario(1L, dto);

        assertEquals("Brenno Atualizado", response.nome());
        verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar com ID inexistente")
    void deveLancarExcecaoAoAtualizarComIdInexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        UsuarioException ex = assertThrows(UsuarioException.class, () -> service.atualizarUsuario(99L, dtoValido));
        assertEquals("Usuário com ID 99 não encontrado.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve deletar usuário com sucesso")
    void deveDeletarUsuarioComSucesso() {
        Usuario usuario = buildUsuario();
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