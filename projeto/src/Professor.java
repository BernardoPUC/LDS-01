import java.util.List;

public class Professor extends Usuario {
  private List<Disciplina> disciplinas;

  public Professor(String nome, String login, String email, String senha) {
    super(nome, login, email, senha);
  }

  public void ListarAlunos() {}

  public void ListarAlunos(Disciplina disciplina) {}
}
