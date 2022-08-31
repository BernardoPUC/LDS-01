
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

  private static void limparTela() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  private static void pausa(Scanner teclado) {
    System.out.println("\033[1;32mEnter para continuar.");
    teclado.nextLine();
  }

  public static void ListarAlunos(Scanner scanner, Usuario usuario) {
    Professor professor = (Professor) usuario;

    while (true) {
      ProfessorFactory.PrintMenu();

      String instrucao = ScannerUtils.lerInstrucao(scanner);
      if (instrucao.equals("0")) {
        limparTela();
        break;
      }

      switch (instrucao) {
        case "1":
          System.out.println("============================================================");
          professor.ListarAlunos();
          System.out.println("============================================================");
          break;

        case "2":
          List<Disciplina> disciplinas = professor.ListarDisciplinas();
          Disciplina disciplinaEscolhida = null;

          System.out.println("============================================================");
          disciplinas.stream().forEach(System.out::println);
          System.out.println("============================================================\n");

          do {
            String codigo = ScannerUtils.lerInstrucao("Digite o código da disciplina escolhida: ", scanner);

            Optional<Disciplina> disciplinaEncontrada = disciplinas.stream()
                .filter(disciplina -> Integer.toString(disciplina.getCodigo()).equals(codigo)).findFirst();

            if (!disciplinaEncontrada.isEmpty()) {
              disciplinaEscolhida = disciplinaEncontrada.get();
            } else {
              System.out.println("Não foi encontrada uma disciplina com o código informado\n");
            }
          } while (disciplinaEscolhida == null);

          professor.ListarAlunos(disciplinaEscolhida);
        default:
          break;
      }

      pausa(scanner);
      limparTela();
    }
  }
}
