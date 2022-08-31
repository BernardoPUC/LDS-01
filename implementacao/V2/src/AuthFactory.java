import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AuthFactory {
  public static Usuario Autenticar(Scanner scanner, Secretaria secretaria) {
    String login = ScannerUtils.lerValor("Login: ", scanner);
    String senha = ScannerUtils.lerValor("Senha: ", scanner);

    if (secretaria.getLogin().equals(login) && secretaria.getSenha().equals(senha)) {
      return (Usuario) secretaria;
    }

    Optional<Object> professorEncontrado = AuthFactory.BuscarProfessorPorCredenciais(secretaria.getProfessores(),
        login, senha);

    if (!professorEncontrado.isEmpty()) {
      System.out.println("\nLogin feito com sucesso!\n");

      return (Usuario) professorEncontrado.get();
    }

    Optional<Object> alunoEncontrado = AuthFactory.BuscarAlunoPorCredenciais(secretaria.getAlunos(), login,
        senha);

    if (!alunoEncontrado.isEmpty()) {
      System.out.println("\nLogin feito com sucesso!\n");

      return (Aluno) alunoEncontrado.get();
    }

    System.out.println("\n\033[1;31mNão foi encontrado um usuário com essas credenciais\n");

    return null;
  }

  private static Optional<Object> BuscarProfessorPorCredenciais(List<Object> lista, String login, String senha) {
    if (lista == null) {
      return Optional.empty();
    }

    return lista.stream().filter(usuario -> {
      if (((Professor) usuario).getNome().equals(login) && ((Professor) usuario).getSenha().equals(senha)) {
        return true;
      } else {
        return false;
      }
    }).findFirst();
  }

  private static Optional<Object> BuscarAlunoPorCredenciais(List<Object> lista,
      String login, String senha) {
    if (lista == null) {
      return Optional.empty();
    }

    return lista.stream().filter(usuario -> {
      if (((Aluno) usuario).getNome().equals(login) && ((Aluno) usuario).getSenha().equals(senha)) {
        return true;
      } else {
        return false;
      }
    }).findFirst();
  }
}
