import java.util.List;

public class Aluno extends Usuario {
  private static final int MAX_DISCIPLINAS_OBG = 4;
  private static final int MAX_DISCIPLINAS_OPT = 2;

  private String matricula;
  private int disciplinasObg = 0;
  private int disciplinasOpt = 0;
  private List<Disciplina> disciplinasMatriculadas;

  public Aluno(String matricula, String nome, String login, String email, String senha) {
    super(nome, login, email, senha);
    this.setMatricula(matricula);
  }

  public void addDisciplina(Disciplina disciplina) {}

  public void removerDisciplina(Disciplina disciplina) {}

  public String getMatricula() {
    return matricula;
  }
  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }
  public int getDisciplinasObg() {
    return disciplinasObg;
  }
  public void setDisciplinasObg(int disciplinasObg) {
    this.disciplinasObg = disciplinasObg;
  }
  public int getDisciplinasOpt() {
    return disciplinasOpt;
  }
  public void setDisciplinasOpt(int disciplinasOpt) {
    this.disciplinasOpt = disciplinasOpt;
  }
}
