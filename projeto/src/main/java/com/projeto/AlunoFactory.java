package com.projeto;

import java.util.Optional;
import java.util.Scanner;

public class AlunoFactory {
  private static void PrintMenuMatricular() {
    System.out.println("=================================================");
    System.out.println("== 1 - Listar disciplinas                      ==");
    System.out.println("== 2 - Escolher disciplina                     ==");
    System.out.println("== 3 - Matricular na disciplina escolhida      ==");
    System.out.println("== 0 - Voltar                                  ==");
    System.out.println("=================================================");
  }

  public static void Matricular(Scanner scanner, Usuario usuario) {
    Aluno aluno = (Aluno) usuario;
    AlunoFactory.PrintMenuMatricular();
    Disciplina disciplinaEscolhida = null;

    String instrucao = ScannerUtils.lerInstrucao(scanner);

    do {
      Secretaria secretaria = Secretaria.getInstance();

      switch (instrucao) {
        case "1":
          secretaria.ListarDisciplinas().stream().filter(disciplina -> disciplina.getCurso().equals(aluno.getCurso())).forEach(System.out::println);
          break;
        case "2": {
          do {
            String codigo = ScannerUtils.lerInstrucao("Digite o código da disciplina escolhida: ", scanner);

            Optional<Disciplina> disciplinaEncontrada = secretaria.ListarDisciplinas().stream().filter(disciplina -> disciplina.getCodigo().equals(codigo)).findFirst();

            if (!disciplinaEncontrada.isEmpty() && disciplinaEncontrada.get().getCurso().equals(aluno.getCurso())) {
              disciplinaEscolhida = disciplinaEncontrada.get();
            } else {
              System.out.println(disciplinaEncontrada == null ? "Não existe uma disciplina com o código informado" : "A disciplina precisa ser do curso do aluno.");
            }
          } while (disciplinaEscolhida == null);

          break;
        }
        case "3": {
          if (disciplinaEscolhida == null) {
            System.out.println("Você precisa escolher uma disciplina antes de poder se matricular");
            break;
          }

          secretaria.SolicitarMatricula(aluno, disciplinaEscolhida);
          break;
        }
        default:
          break;
      }
    } while (!instrucao.equals("0"));
  }

  private static void PrintMenuCancelarMatricula() {
    System.out.println("====================================================");
    System.out.println("== 1 - Listar disciplinas                         ==");
    System.out.println("== 2 - Escolher disciplina                        ==");
    System.out.println("== 3 - Cancelar matricula na disciplina escolhida ==");
    System.out.println("== 0 - Voltar                                     ==");
    System.out.println("====================================================");
  }

  public static void CancelarMatricula(Scanner scanner, Usuario usuario) {
    Aluno aluno = (Aluno) usuario;
    AlunoFactory.PrintMenuCancelarMatricula();
    Disciplina disciplinaEscolhida = null;

    String instrucao = ScannerUtils.lerInstrucao(scanner);

    do {
      Secretaria secretaria = Secretaria.getInstance();

      switch (instrucao) {
        case "1":
          aluno.ListarDisciplinasMatriculadas().stream().forEach(System.out::println);
          break;
        case "2": {
          do {
            String codigo = ScannerUtils.lerInstrucao("Digite o código da disciplina escolhida: ", scanner);

            Optional<Disciplina> disciplinaEncontrada = aluno.ListarDisciplinasMatriculadas().stream().filter(disciplina -> disciplina.getCodigo().equals(codigo)).findFirst();

            if (!disciplinaEncontrada.isEmpty()) {
              disciplinaEscolhida = disciplinaEncontrada.get();
            } else {
              System.out.println("Você não está matriculado na disciplina com o código informado");
            }
          } while (disciplinaEscolhida == null);

          break;
        }
        case "3": {
          if (disciplinaEscolhida == null) {
            System.out.println("Você precisa escolher uma disciplina");
            break;
          }

          secretaria.SolicitarMatricula(aluno, disciplinaEscolhida);
          break;
        }
        default:
          break;
      }
    } while (!instrucao.equals("0"));
  }
}