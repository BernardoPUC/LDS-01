import java.util.List;
import java.util.Map;

public class Secretaria {
    private List<Professor> professores;
    private List<Disciplina> disciplinas;
    private Map<String, Curso> cursos;
    private Map<String, Aluno> alunos;
    private SistemaFinanceiro sistemaFinanceiro;
    private Boolean permiteAlteracaoDeMatricula;
    private static Secretaria secretaria;

    private Secretaria() {}

    public static Secretaria getInstance() {
      if (secretaria == null) {
        secretaria = new Secretaria();
      }

      return secretaria;
    }

    public Curriculo GerarCurriculo() {
      return new Curriculo();
    }
    
    public void SolicitarMatricula(Aluno aluno, Disciplina disciplina) {
      aluno.addDisciplina(disciplina);
    }

    public void SolicitarCancelamentoMatricula(Aluno aluno, Disciplina disciplina) {
      aluno.removerDisciplina(disciplina);
    }

    public void InscreverParaSemestre(Aluno aluno) {
      this.NotificarFinanceiro(aluno);
    }

    private void NotificarFinanceiro(Aluno aluno) {

    }

    public List<Professor> ListarProfessores() {
      return this.professores;
    }

    public List<Aluno> ListarAlunos() {
      return this.alunos;
    }
}
