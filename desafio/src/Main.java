import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UsuarioService service = new UsuarioService();
        int op = -1;

        do {
            Menu.mostrarMenu();
            try {
                op = sc.nextInt();
                sc.nextLine();
                switch (op) {

                    case 1:
                        System.out.println("\n--- CADASTRAR USUÁRIO ---");
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();

                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        System.out.print("Senha: ");
                        String senha = sc.nextLine();

                        service.criarUsuario(nome, email, senha);
                        System.out.println("Usuário cadastrado com sucesso!");
                        break;

                    case 2:
                        System.out.println("\n--- LISTA DE USUÁRIOS ---");
                        service.listarUsuarios();
                        break;

                    case 3:
                        System.out.println("\n--- BUSCAR USUÁRIO POR ID ---");
                        System.out.print("Digite o ID do usuário: ");
                        int idBuscar = sc.nextInt();

                        Usuario usuario = service.buscarUsuarioPorId(idBuscar);
                        usuario.exibirUsuario();
                        break;

                    case 4:
                        System.out.println("\n--- ATUALIZAR USUÁRIO ---");
                        System.out.print("Digite o ID do usuário: ");
                        int idAtualizar = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Novo nome: ");
                        String novoNome = sc.nextLine();

                        service.atualizarUsuario(idAtualizar, novoNome);
                        System.out.println("Usuário atualizado com sucesso!");
                        break;

                    case 5:
                        System.out.println("\n--- DELETAR USUÁRIO ---");
                        System.out.print("Digite o ID do usuário: ");
                        int idDeletar = sc.nextInt();

                        service.deletarUsuario(idDeletar);
                        System.out.println("Usuário removido com sucesso!");
                        break;

                    case 0:
                        System.out.println("\nEncerrando sistema...");
                        break;

                    default:
                        System.out.println("\nOpção inválida.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("\nErro: digite apenas números.");
                sc.nextLine();
            }
            catch (UsuarioException e) {
                System.out.println("\nErro: " + e.getMessage());
            }
            catch (Exception e) {
                System.out.println("\nErro inesperado: " + e.getMessage());
            }
        } while (op != 0);
        sc.close();
    }
}