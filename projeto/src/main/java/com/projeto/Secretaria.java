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

    public void MatricularAluno(Aluno aluno) throws IllegalArgumentException {
      Aluno alunoComMesmaMatricula = this.alunos.get(aluno.getMatricula());

      if (alunoComMesmaMatricula != null) {
        throw new IllegalArgumentException("Já existe um aluno com essa matricula");
      }

      this.alunos.putIfAbsent(aluno.getMatricula(), aluno);
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
      if (this.alunos == null) {
        return null;
      }

      return List.copyOf(this.alunos.values());
    }

    public List<Disciplina> ListarDisciplinas() {
      return this.disciplinas;
    }
}
