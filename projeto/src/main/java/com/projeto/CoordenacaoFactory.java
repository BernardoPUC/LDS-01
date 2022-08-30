package com.projeto;

import java.util.Scanner;

public class CoordenacaoFactory {
  private void PrintMenuAluno() {
    System.out.println("======================================================");
    System.out.println("== 1 - Cadastrar dados do aluno                     ==");
    System.out.println("== 2 - Escolher curso do aluno                      ==");
    System.out.println("== 0 - Voltar                                       ==");
    System.out.println("======================================================");
  }

  public static void CadastrarAluno(Scanner scanner) {
    String nome = ScannerUtils.lerValor("Nome do aluno: ", scanner);
    String login = ScannerUtils.lerValor("Login do aluno: ", scanner);
    String email = ScannerUtils.lerValor("Email do aluno: ", scanner);
    String senha = ScannerUtils.lerValor("Senha do aluno: ", scanner);
  }

  public static void CadastrarProfessor() {}

  public static void CadastrarCurso() {}

  public static void CadastrarCurriculo() {}
}
