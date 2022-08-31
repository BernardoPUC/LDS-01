package com.projeto;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
  private static final int LIMITE_ALUNOS = 60;
  private static final int MIN_ALUNOS = 3;
  private static int nextCodigo = 0;

  private Professor professor;
  private int codigo;
  private TipoDisciplina tipo;
  private Curso curso;
  private Boolean ativa;
  private Double valor;
  private List<Aluno> alunos;

  public Disciplina(Professor professor, TipoDisciplina tipo, Curso curso, Double valor) {
    this.codigo = ++Disciplina.nextCodigo;
    this.professor = professor;
    this.tipo = tipo;
    this.curso = curso;
    this.valor = valor;
    this.alunos = new ArrayList<Aluno>();
  }

  public void Matricular(Aluno aluno) {
    int qtdeAlunos = this.alunos.size();

    if (qtdeAlunos < Disciplina.LIMITE_ALUNOS) {
      this.alunos.add(aluno);

      this.setAtiva(qtdeAlunos > Disciplina.MIN_ALUNOS);
    } else {
      throw new IllegalStateException("A sala para a disciplina está cheia");
    }
  }

  public void CancelarMatricula(Aluno aluno) {
    Boolean isAlunoMatriculado = this.alunos.contains(aluno);

    if (!isAlunoMatriculado) {
      throw new IllegalArgumentException("O aluno em questão não está matriculado na disciplina");
    } else {
      this.alunos.remove(aluno);
      int qtdeAlunos = this.alunos.size();

      this.setAtiva(qtdeAlunos > Disciplina.MIN_ALUNOS);
    }
  }

  public List<Aluno> ListarAlunos() {
    return this.alunos;
  }

  public int getCodigo() {
    return codigo;
  }

  public Professor getProfessor() {
    return professor;
  }
  
  public TipoDisciplina getTipo() {
    return tipo;
  }

  public void setTipo(TipoDisciplina tipo) {
    this.tipo = tipo;
  }

  public Boolean getAtiva() {
    return ativa;
  }

  public void setAtiva(Boolean ativa) {
    this.ativa = ativa;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public Curso getCurso() {
    return curso;
  }

  public void setCurso(Curso curso) {
    this.curso = curso;
  }
}
