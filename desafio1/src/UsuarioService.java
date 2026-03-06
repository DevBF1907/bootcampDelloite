import java.util.ArrayList;

public class UsuarioService {

    ArrayList<Usuario> usuarios = new ArrayList<>();


    public void criarUsuario(String nome, String email, String senha) {

        if (nome == null || nome.isBlank()) {
            throw new UsuarioException("Nome não pode ser vazio.");
        }

        if (nomeJaExiste(nome)) {
            throw new UsuarioException("Já existe um usuário com esse nome.");
        }

        if (email == null || email.isBlank()) {
            throw new UsuarioException("Email não pode ser vazio.");
        }

        if (!email.contains("@gmail.com")) {
            throw new UsuarioException("Email inválido. Deve conter '@gmail.com'.");
        }

        if (emailJaExiste(email)) {
            throw new UsuarioException("Esse email já está cadastrado.");
        }

        Usuario usuario = new Usuario(nome, email, senha);
        usuarios.add(usuario);
    }


    public void listarUsuarios() {

        if (usuarios.isEmpty()) {
            throw new UsuarioException("Nenhum usuário cadastrado.");
        }

        for (Usuario u : usuarios) {
            u.exibirUsuario();
        }
    }


    public Usuario buscarUsuarioPorId(int id) {

        for (Usuario u : usuarios) {
            if (u.id == id) {
                return u;
            }
        }

        throw new UsuarioException("Usuário com ID " + id + " não encontrado.");
    }


    public void atualizarUsuario(int id, String novoNome) {

        Usuario usuario = buscarUsuarioPorId(id);

        if (novoNome == null || novoNome.isBlank()) {
            throw new UsuarioException("Nome não pode ser vazio.");
        }

        usuario.nome = novoNome;
    }


    public void deletarUsuario(int id) {

        Usuario usuario = buscarUsuarioPorId(id);

        usuarios.remove(usuario);
    }


    public boolean emailJaExiste(String email) {

        for (Usuario u : usuarios) {
            if (u.email.equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean nomeJaExiste(String nome) {

        for (Usuario u : usuarios) {
            if (u.nome.equalsIgnoreCase(nome)) {
                return true;
            }
        }

        return false;
    }
}