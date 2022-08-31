package com.projeto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CoordenacaoFactory {
  private static void PrintMenuAluno() {
    System.out.println("======================================================");
    System.out.println("== 1 - Cadastrar dados do aluno                     ==");
    System.out.println("== 2 - Escolher curso do aluno                      ==");
    System.out.println("== 3 - Finalizar cadastro                           ==");
    System.out.println("== 0 - Voltar                                       ==");
    System.out.println("======================================================");
  }

  public static void CadastrarAluno(Scanner scanner) {
    Secretaria secretaria = Secretaria.getInstance();

    String nome = "";
    String login = "";
    String email = "";
    String senha = "";
    Curso curso = null;
    String instrucao;

    do {
      CoordenacaoFactory.PrintMenuAluno();
      instrucao = ScannerUtils.lerInstrucao(scanner);

      switch (instrucao) {
        case "1":
          nome = ScannerUtils.lerValor("Nome do aluno: ", scanner);
          login = ScannerUtils.lerValor("Login do aluno: ", scanner);
          email = ScannerUtils.lerValor("Email do aluno: ", scanner);
          senha = ScannerUtils.lerValor("Senha do aluno: ", scanner);
          break;
        case "2": {
          List<Curso> cursos = secretaria.ListarCursos();

          System.out.println("======================================================");
          cursos.stream().forEach(System.out::println);
          System.out.println("======================================================");

          String idCurso = ScannerUtils.lerValor("Digite o id do curso desejado: ", scanner, cursos.stream().map(c -> Integer.toString(c.getId())).toList());

          curso = secretaria.BuscarCurso(idCurso);
          break;
        }
        case "3": {
          Aluno aluno = new Aluno(nome, login, email, senha, curso);

          try {
            secretaria.MatricularAluno(aluno); 
          } catch (Exception e) {
            System.out.println(e);
          }
        }
        default:
          break;
      }
    } while (!instrucao.equals("0"));
  }

  public static void CadastrarProfessor(Scanner scanner) {
    Secretaria secretaria = Secretaria.getInstance();

    String nome = ScannerUtils.lerValor("Nome do professor: ", scanner);
    String login = ScannerUtils.lerValor("Login do professor: ", scanner);
    String email = ScannerUtils.lerValor("Email do professor: ", scanner);
    String senha = ScannerUtils.lerValor("Senha do professor: ", scanner);

    Professor professor = new Professor(nome, login, email, senha);

    secretaria.AdicionarProfessor(professor);
  }

  public static void CadastrarCurso(Scanner scanner) {
    Secretaria secretaria = Secretaria.getInstance();

    String nome = ScannerUtils.lerValor("Nome do curso: ", scanner);
    
    int qtdeCreditos = -1;

    do {
      try {
        String qtdeCreditosStr = ScannerUtils.lerValor("Quantidade de créditos do curso: ", scanner);

        qtdeCreditos = Integer.parseInt(qtdeCreditosStr);
      } catch (Exception e) {
        System.out.println("O valor informado precisa ser um número");
      }
    } while (qtdeCreditos == -1);

    Curso curso = new Curso(nome, qtdeCreditos);

    secretaria.AdicionarCurso(curso);
  }

  private static void PrintMenuGerarCurriculo() {
    System.out.println("======================================================");
    System.out.println("== 1 - Definir curso                                ==");
    System.out.println("== 2 - Definir disciplinas                          ==");
    System.out.println("== 3 - Salvar cadastro                              ==");
    System.out.println("======================================================");
  }

  private static void PrintMenuDisciplinas() {
    System.out.println("======================================================");
    System.out.println("== 1 - Listar disciplinas                           ==");
    System.out.println("== 2 - Adicionar disciplina                         ==");
    System.out.println("== 3 - Remover disciplina                           ==");
    System.out.println("======================================================");
  }

  public static void GerarCurriculo(Scanner scanner) {
    CoordenacaoFactory.PrintMenuGerarCurriculo();
    Secretaria secretaria = Secretaria.getInstance();
    
    Curso curso = null;
    Map<String, Disciplina> disciplinasAdicionadas = new HashMap<String, Disciplina>();
    String instrucao = "";

    do {
      CoordenacaoFactory.PrintMenuAluno();
      instrucao = ScannerUtils.lerInstrucao(scanner);

      switch (instrucao) {
        case "1": {
          List<Curso> cursos = secretaria.ListarCursos();

          System.out.println("======================================================");
          cursos.stream().forEach(System.out::println);
          System.out.println("======================================================");

          String idCurso = ScannerUtils.lerValor("Digite o id do curso desejado: ", scanner, cursos.stream().map(c -> Integer.toString(c.getId())).toList());

          curso = secretaria.BuscarCurso(idCurso);
          break;
        }
        case "2": {
          String instrucaoDisciplinas;
          List<Disciplina> disciplinas = secretaria.ListarDisciplinas();

          do {
            CoordenacaoFactory.PrintMenuDisciplinas();
            instrucaoDisciplinas = ScannerUtils.lerInstrucao(scanner);

            switch (instrucaoDisciplinas) {
              case "1": {
                System.out.println("======================================================");
                disciplinas.stream().forEach(System.out::println);
                System.out.println("======================================================");
                break;
              }
              case "2": {
                String disciplinaAdicionada = ScannerUtils.lerValor("Digite o código da disciplina a ser adicionada: ", scanner, disciplinas.stream().map(di -> Integer.toString(di.getCodigo())).toList());
                Disciplina disciplinaInstance = disciplinas.stream().filter(d -> Integer.toString(d.getCodigo()).equals(disciplinaAdicionada)).findFirst().get();

                disciplinasAdicionadas.putIfAbsent(disciplinaAdicionada, disciplinaInstance);
              }
              case "3": {
                String disciplinaRemovida = ScannerUtils.lerValor("Digite o código da disciplina a ser removida: ", scanner, disciplinas.stream().map(di -> Integer.toString(di.getCodigo())).toList());
                Disciplina disciplinaInstance = disciplinasAdicionadas.get(disciplinaRemovida);

                disciplinasAdicionadas.remove(disciplinaRemovida, disciplinaInstance);
                break;
              }
              default:
                break;
            }

          } while (!instrucaoDisciplinas.equals("0"));


          break;
        }
        case "3": {
          if (curso == null) {
            System.out.println("Você precisa definir o curso");
            break;
          }

          if (disciplinasAdicionadas.size() == 0) {
            System.out.println("Você precisa definir as disciplinas do curriculo");
            break;
          }

          Curriculo curriculo = new Curriculo(curso, List.copyOf(disciplinasAdicionadas.values()));

          Secretaria.getInstance().GerarCurriculo(curriculo, curso);

          break;
        }
        default:
          break;
      }
    } while (!instrucao.equals("0"));

  }
}
