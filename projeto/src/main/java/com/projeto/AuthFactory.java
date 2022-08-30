package com.projeto;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AuthFactory {
  public static Usuario Autenticar(Scanner scanner) {
    String login = ScannerUtils.lerInstrucao("Login: ", scanner);
    String senha = ScannerUtils.lerInstrucao("Senha: ", scanner);   

    Secretaria secretaria = Secretaria.getInstance();

    Optional<Professor> professorEncontrado = AuthFactory.BuscarProfessorPorCredenciais(secretaria.ListarProfessores(), login, senha);    

    if (!professorEncontrado.isEmpty()) {
      return (Usuario) professorEncontrado.get();
    }
    
    Optional<Aluno> alunoEncontrado = AuthFactory.BuscarAlunoPorCredenciais(secretaria.ListarAlunos(), login, senha);

    if (!alunoEncontrado.isEmpty()) {
      return (Aluno) alunoEncontrado.get();
    }

    return null;
  }

  private static Optional<Professor> BuscarProfessorPorCredenciais(List<Professor> lista, String login, String senha) {
    return lista.stream().filter(usuario -> {
      if (usuario.getNome().equals(login) && usuario.getSenha().equals(senha)) {
        return true;
      } else {
        return false;
      }
    }).findFirst();
  }

  private static Optional<Aluno> BuscarAlunoPorCredenciais(List<Aluno> lista, String login, String senha) {
    return lista.stream().filter(usuario -> {
      if (usuario.getNome().equals(login) && usuario.getSenha().equals(senha)) {
        return true;
      } else {
        return false;
      }
    }).findFirst();
  }
}
