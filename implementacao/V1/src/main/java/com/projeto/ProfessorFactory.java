package com.projeto;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProfessorFactory {
  private static void PrintMenu() {
    System.out.println("============================================================");
    System.out.println("== 1 - Listar os alunos de todas as disciplinas           ==");
    System.out.println("== 2 - Listar os alunos de uma disciplina especifica      ==");
    System.out.println("== 0 - Voltar                                             ==");
    System.out.println("============================================================");
  }

  public static void ListarAlunos(Scanner scanner, Usuario usuario) {
    Professor professor = (Professor) usuario;
    ProfessorFactory.PrintMenu();

    String instrucao = ScannerUtils.lerInstrucao(scanner);

    do {
      switch (instrucao) {
        case "1": {
          System.out.println("============================================================");
          professor.ListarAlunos();
          System.out.println("============================================================");
          break;
        }
        case "2": {
          List<Disciplina> disciplinas = professor.ListarDisciplinas();
          Disciplina disciplinaEscolhida = null;

          System.out.println("============================================================");
          disciplinas.stream().forEach(System.out::println);
          System.out.println("============================================================\n");

          do {
            String codigo = ScannerUtils.lerInstrucao("Digite o código da disciplina escolhida: ", scanner);

            Optional<Disciplina> disciplinaEncontrada = disciplinas.stream().filter(disciplina -> Integer.toString(disciplina.getCodigo()).equals(codigo)).findFirst();

            if (!disciplinaEncontrada.isEmpty()) {
              disciplinaEscolhida = disciplinaEncontrada.get();
            } else { 
              System.out.println("Não foi encontrada uma disciplina com o código informado\n");
            }
          } while (disciplinaEscolhida == null);

          professor.ListarAlunos(disciplinaEscolhida);
        }
        default:
          break;
      }
    } while (!instrucao.equals("0"));
  }
}
