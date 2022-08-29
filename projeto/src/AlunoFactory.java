public class AlunoFactory {
  private static void printMenuMatricular() {
    System.out.println("=================================================");
    System.out.println("== 1 - Listar disciplinas                      ==");
    System.out.println("== 2 - Escolher disciplina                     ==");
    System.out.println("== 3 - Matricular na disciplina escolhida      ==");
    System.out.println("== 0 - Voltar                                  ==");
    System.out.println("=================================================");
  }

  public static void matricular(Scanner scanner, Aluno aluno) {
    AlunoFactory.printMenuMatricular();
    Disciplina disciplinaEscolhida = null;

    String instrucao = ScannerUtils.lerInstrucao(scanner);

    do {
      Secretaria secretaria = Secretaria.getInstance();

      switch (instrucao) {
        case "1":
          secretaria.ListarDisciplinas().stream().forEach(System.out::println);
          break;
        case "2": {
          do {
            String codigo = ScannerUtils.lerInstrucao("Digite o código da disciplina escolhida: ", scanner);

            Disciplina disciplinaEncontrada = secretaria.ListarDisciplinas().stream().findFirst(disciplina -> disciplina.getCodigo().equals(codigo));

            if (disciplinaEncontrada != null && disciplinaEncontrada.getCurso().equals(aluno.getCurso())) {
              disciplinaEscolhida = disciplinaEncontrada;
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

  private static void printMenuCancelarMatricula() {
    System.out.println("====================================================");
    System.out.println("== 1 - Listar disciplinas                         ==");
    System.out.println("== 2 - Escolher disciplina                        ==");
    System.out.println("== 3 - Cancelar matricula na disciplina escolhida ==");
    System.out.println("== 0 - Voltar                                     ==");
    System.out.println("====================================================");
  }

  public static void cancelarMatricula(Scanner scanner, Aluno aluno) {
    AlunoFactory.printMenuCancelarMatricula();
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

            Disciplina disciplinaEncontrada = aluno.ListarDisciplinasMatriculadas().stream().findFirst(disciplina -> disciplina.getCodigo().equals(codigo));

            if (disciplinaEncontrada != null) {
              disciplinaEscolhida = disciplinaEncontrada;
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
