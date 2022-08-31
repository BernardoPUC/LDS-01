package com.projeto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Secretaria {
    private List<Professor> professores;
    private List<Disciplina> disciplinas;
    private Map<String, Curso> cursos;
    private Map<String, Aluno> alunos;
    private SistemaFinanceiro sistemaFinanceiro;
    private Boolean permiteAlteracaoDeMatricula;
    private static Secretaria secretaria;

    private Secretaria() {
      this.professores = new ArrayList<Professor>();
      this.disciplinas = new ArrayList<Disciplina>();
      this.cursos = new HashMap<String, Curso>();
      this.alunos = new HashMap<String, Aluno>();
      this.sistemaFinanceiro = SistemaFinanceiro.getInstance();
    }

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
      aluno.AddDisciplina(disciplina);
    }

    public void SolicitarCancelamentoMatricula(Aluno aluno, Disciplina disciplina) {
      aluno.RemoverDisciplina(disciplina);
    }

    public void MatricularAluno(Aluno aluno) throws IllegalArgumentException {
      Aluno alunoComMesmaMatricula = this.alunos.get(aluno.getMatricula());

      if (alunoComMesmaMatricula != null) {
        throw new IllegalArgumentException("Já existe um aluno com essa matricula");
      }

      this.alunos.putIfAbsent(aluno.getMatricula(), aluno);
    }

    public void AdicionarProfessor(Professor professor) {
      this.professores.add(professor);
    }

    public void AtribuirDisciplina(Disciplina desciplina, Professor professor) {
      Boolean disciplinaEncontrada = professor.ListarDisciplinas().stream().filter(d -> d.getCodigo().equals(disciplina.getCodigo())).findFirst().isPresent();
      Secretaria secretaria = Secretaria.getInstance();
  
      if (!disciplinaEncontrada) {
        if (disciplina.getProfessor() != null) {
          disciplina.getProfessor().RemoverDisciplina(disciplina);
        }

        professor.AdicionarDisciplina(disciplina);
      }
    }

    public void AdicionarCurso(Curso curso) {
      this.cursos.putIfAbsent(curso.getId(), curso);
    }

    public void InscreverParaSemestre(Aluno aluno) {
      this.NotificarFinanceiro(aluno);
    }

    private void NotificarFinanceiro(Aluno aluno) {

    }

    public Curso BuscarCurso(String idCurso) {
      if (this.cursos == null) {
        return null;
      }

      return this.cursos.get(idCurso);
    }

    public List<Professor> ListarProfessores() {
      return this.professores;
    }

    public List<Aluno> ListarAlunos() {
      if (this.alunos == null) {
        return null;
      }

      return List.copyOf(this.alunos.values());
    }

    public List<Curso> ListarCursos() {
      if (this.cursos == null) {
        return null;
      }

      return List.copyOf(this.cursos.values());
    }

    public List<Disciplina> ListarDisciplinas() {
      return this.disciplinas;
    }
}
