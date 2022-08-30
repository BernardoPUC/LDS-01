public class AuthFactory {
  public static Usuario Autenticar(Scanner scanner) {
    String login = ScannerUtils.lerInstrucao("Login: ", scanner);
    String senha = ScannerUtils.lerInstrucao("Senha: ", scanner);   

    Secretaria secretaria = Secretaria.getInstance();

    Usuario usuarioEncontrado = null;

    usuarioEncontrado = AuthFactory.BuscarPorCredenciais(secretaria.ListarProfessores(), login, senha);

    if (usuarioEncontrado == null) {
      usuarioEncontrado = AuthFactory.BuscarPorCredenciais(secretaria.ListarAlunos(), login, senha);
    }

    return usuarioEncontrado;
  }

  private static Usuario BuscarPorCredenciais(List<Usuario> lista, String login, String senha) {
    return lista.stream().findFirst(usuario -> {
      if (usuario.getName().equals(login) && usuario.getSenha().equals(senha)) {
        return true;
      } else {
        return false;
      }
    });
  }
}
