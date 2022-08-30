package com.projeto;

import java.util.List;

public class Aluno extends Usuario {
  private static final int MAX_DISCIPLINAS_OBG = 4;
  private static final int MAX_DISCIPLINAS_OPT = 2;

  private String matricula;
  private Curso curso;
  private int disciplinasObg = 0;
  private int disciplinasOpt = 0;
  private List<Disciplina> disciplinasMatriculadas;

  public Aluno(String matricula, String nome, String login, String email, String senha) {
    super(nome, login, email, senha);
    this.setMatricula(matricula);
  }

  public List<Disciplina> ListarDisciplinasMatriculadas() {
    return this.disciplinasMatriculadas;
  }

  public void addDisciplina(Disciplina disciplina) {
    try {
      Boolean isDisciplinaObrigatoria = disciplina.getTipo() == TipoDisciplina.OBRIGATORIA;
      Boolean disciplinaMatriculada = this.disciplinasMatriculadas.contains(disciplina);

      if (disciplinaMatriculada) {
        throw new IllegalArgumentException("Disciplina já matriculada");
      }

      if (isDisciplinaObrigatoria) {
        this.addDisciplina(disciplina, Aluno.MAX_DISCIPLINAS_OBG, this.disciplinasObg, "obrigatórias");
        this.disciplinasObg++;
      } else {
        this.addDisciplina(disciplina, Aluno.MAX_DISCIPLINAS_OPT, this.disciplinasOpt, "optativas");
        this.disciplinasOpt++;
      }
    } catch (Exception e) {
      System.out.println(e + "\n");
    }
  }

  private void addDisciplina(Disciplina disciplina, int maxPossivel, int curDisciplinasMatriculadas, String tipoDisciplina) {
    if (curDisciplinasMatriculadas < maxPossivel) {
      disciplina.Matricular(this);
    } else {
      throw new IllegalStateException("Só é possivel se matricular em no máximo " + maxPossivel + " disciplinas " + tipoDisciplina);
    }
  }

  public void removerDisciplina(Disciplina disciplina) {
    try {
      Boolean isDisciplinaObrigatoria = disciplina.getTipo() == TipoDisciplina.OBRIGATORIA;
      Boolean disciplinaMatriculada = this.disciplinasMatriculadas.contains(disciplina);

      if (!disciplinaMatriculada) {
        throw new IllegalArgumentException("Disciplina não matriculada");
      }

      disciplina.CancelarMatricula(this);

      if (isDisciplinaObrigatoria) {
        this.disciplinasObg--;
      } else {
        this.disciplinasOpt--;
      }
    } catch (Exception e) {
      System.out.println(e + "\n");
    }
  }

  public String getMatricula() {
    return matricula;
  }
  
  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public Curso getCurso() {
    return curso;
  }

  public void setCurso(Curso curso) {
    this.curso = curso;
  }
}
