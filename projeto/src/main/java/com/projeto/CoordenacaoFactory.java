package com.projeto;

import java.util.Scanner;

public class CoordenacaoFactory {
  private void PrintMenuAluno() {
    System.out.println("======================================================");
    System.out.println("== 1 - Cadastrar dados do aluno                     ==");
    System.out.println("== 2 - Escolher curso do aluno                      ==");
    System.out.println("== 3 - Finalizar cadastro                           ==");
    System.out.println("== 0 - Voltar                                       ==");
    System.out.println("======================================================");
  }

  public static void CadastrarAluno(Scanner scanner) {
    Secretaria secretaria = Secretaria.getInstance();

    String nome;
    String login;
    String email;
    String senha;
    Curso curso;
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

          String idCurso = ScannerUtils.lerValor("Digite o id do curso desejado: ", scanner, cursos);

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
    nome = ScannerUtils.lerValor("Nome do professor: ", scanner);
    login = ScannerUtils.lerValor("Login do professor: ", scanner);
    email = ScannerUtils.lerValor("Email do professor: ", scanner);
    senha = ScannerUtils.lerValor("Senha do professor: ", scanner);

    Professor professor = new Professor(nome, login, email, senha);

    secretaria.AdicionarProfessor(professor);
  }

  public static void CadastrarCurso(Scanner scanner) {
    nome = ScannerUtils.lerValor("Nome do curso: ", scanner);
    qtdeCreditos = ScannerUtils.lerValor("Quantidade de créditos do curso: ", scanner);

    Curso curso = new Curso(nome, qtdeCreditos);

    secretaria.AdicionarCurso(curso);
  }

  private static void PrintMenuGerarCurriculo() {
    System.out.println("======================================================");
    System.out.println("== 1 - Definir curso                                ==");
    System.out.println("== 2 - Definir disciplinas                          ==");
    System.out.println("======================================================");
  }

  private static void PrintMenuDisciplinas() {
    System.out.println("======================================================");
    System.out.println("== 1 - Listar disciplinas                           ==");
    System.out.println("== 2 - Adicionar disciplina                         ==");
    System.out.println("== 3 - Remover disciplina                           ==");
    System.out.println("======================================================");
  }

  public static void GerarCurriculo(Scanner scannner) {
    CoordenacaoFactory.PrintMenuGerarCurriculo();
    Secretaria secretaria = Secretaria.getInstance();
    
    Curso curso;
    Map<String, Disciplina> disciplinasAdicionadas = new HashMap<String, Disciplina>();

    do {
      CoordenacaoFactory.PrintMenuAluno();
      instrucao = ScannerUtils.lerInstrucao(scanner);

      switch (instrucao) {
        case "1": {
          List<Curso> cursos = secretaria.ListarCursos();

          System.out.println("======================================================");
          cursos.stream().forEach(System.out::println);
          System.out.println("======================================================");

          String idCurso = ScannerUtils.lerValor("Digite o id do curso desejado: ", scanner, cursos.stream().map(c -> c.getId()));

          curso = secretaria.BuscarCurso(idCurso);
          break;
        }
        case "2": {
          String instrucaoDisciplinas;
          List<Disciplina> disciplinas = secretaria.ListarDisciplinas();

          do {
            CoordenacaoFactory.PrintMenuDisciplinas();
            instrucaoDisciplinas = ScannerUtils.lerInstrucao();

            switch (instrucaoDisciplinas) {
              case "1": {
                System.out.println("======================================================");
                disciplinas.stream().forEach(System.out::println);
                System.out.println("======================================================");
                break;
              }
              case "2": {
                String disciplinaAdicionada = ScannerUtils.lerValor("Digite o código da disciplina a ser adicionada: ", scanner, disciplinas.map(d -> d.getCodigo()));

                disciplinasAdicionadas.putIfAbsent(disciplinaAdicionada, disciplinas.get(disciplinaAdicionada));
              }
              default:
                break;
            }

          } while (!instrucaoDisciplinas.equals("0"));


          break;
        }
        default:
          break;
      }
    } while (!instrucao.equals("0"));

  }
}
