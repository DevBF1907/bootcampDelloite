public class Usuario {
    static int cont = 1;

    int id;
    String nome;
    String email;
    String senha;

    public Usuario(String nome, String email, String senha){
        this.id = cont++;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void exibirUsuario() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("---------------------");
    }
}