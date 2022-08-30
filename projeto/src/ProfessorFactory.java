public class ProfessorFactory {
  private static void PrintMenu() {
    System.out.println("============================================================");
    System.out.println("== 1 - Listar os alunos de todas as disciplinas           ==");
    System.out.println("== 2 - Listar os alunos de uma disciplina especifica      ==");
    System.out.println("== 0 - Voltar                                             ==");
    System.out.println("============================================================");
  }

  public static void ListarAlunos(Scanner scanner, Professor professor) {
    ProfessorFactory.PrintMenu();

    String instrucao = ScannerUtils.lerInstrucao(scanner);

    do {
      switch (instrucao) {
        case "1":
          professor.ListarAlunos();
          break;
        case "2": {
          String codigo;
          List<Disciplina> disciplinas = professor.ListarDisciplinas();
          Disciplina disciplinaEscolhida = null;

          System.out.println("============================================================");
          disciplinas.stream().forEach(System.out::println);
          System.out.println("============================================================\n");

          do {
            codigo = ScannerUtils.lerInstrucao("Digite o código da disciplina escolhida: ", scanner);

            Disciplina disciplinaEncontrada = disciplinas.stream().findFirst(disciplina -> disciplina.getCodigo().equals(codigo));

            if (disciplinaEncontrada != null) {
              disciplinaEscolhida = disciplinaEncontrada;
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
