

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>();
        int op = 0;
        boolean exec = true;

        while (exec) {

            System.out.println("\n=====================================");
            System.out.println("      SISTEMA DE USUÁRIOS - CRUD     ");
            System.out.println("=====================================");
            System.out.println("1 - Criar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Atualizar usuário");
            System.out.println("4 - Remover usuário");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:
                    System.out.println("\n--- CRIAR USUÁRIO ---");
                    System.out.print("Digite o nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Digite o email: ");
                    String email = sc.nextLine();
                    System.out.print("Digite a senha: ");
                    String senha = sc.nextLine();


                    boolean emailExistente = false;
                    for (Usuario u : usuarios) {
                        if (u.getEmail().equals(email)) {
                            emailExistente = true;
                            break;
                        }
                    }

                    if (emailExistente) {
                        System.out.println("Erro: Email já cadastrado!");
                    } else {
                        usuarios.add(new Usuario(nome, email, senha));
                        System.out.println("Usuário criado com sucesso!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE USUÁRIOS ---");
                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        for (Usuario u : usuarios) {
                            System.out.println("Nome: " + u.getNome() + " | Email: " + u.getEmail());
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- ATUALIZAR USUÁRIO ---");
                    System.out.print("Digite o email do usuário que deseja atualizar: ");
                    String emailAtualizar = sc.nextLine();
                    boolean encontrado = false;

                    for (Usuario u : usuarios) {
                        if (u.getEmail().equals(emailAtualizar)) {
                            encontrado = true;
                            System.out.print("Novo nome: ");
                            String novoNome = sc.nextLine();
                            System.out.print("Nova senha: ");
                            String novaSenha = sc.nextLine();

                            u.setNome(novoNome);
                            u.setSenha(novaSenha);

                            System.out.println("Usuário atualizado com sucesso!");
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("Usuário não encontrado!");
                    }
                    break;

                case 4:
                    System.out.println("\n--- REMOVER USUÁRIO ---");
                    System.out.print("Digite o email do usuário que deseja remover: ");
                    String emailRemover = sc.nextLine();
                    boolean removido = false;

                    for (int i = 0; i < usuarios.size(); i++) {
                        if (usuarios.get(i).getEmail().equals(emailRemover)) {
                            usuarios.remove(i);
                            System.out.println("Usuário removido com sucesso!");
                            removido = true;
                            break;
                        }
                    }
                    if (!removido) {
                        System.out.println("Usuário não encontrado!");
                    }
                    break;

                case 5:
                    System.out.println("Saindo do sistema... Até mais!");
                    exec =  false;
                    break;

                default:
                    System.out.println("Opção inválida! Digite um número entre 1 e 5.");
            }
        }
    }
}