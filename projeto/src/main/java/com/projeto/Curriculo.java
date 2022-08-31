package com.projeto;

import java.util.List;

public class Curriculo {
  private static int nextCodigo = 0;

  private int codigo;
  private Curso curso;
  private List<Disciplina> disciplinas;

  public Curriculo(Curso curso, List<Disciplina> disciplinas) {
    this.curso = curso;
    this.disciplinas = disciplinas;
    this.codigo = ++Curriculo.nextCodigo;
  }

  public int getCodigo() {
    return codigo;
  }

  public List<Disciplina> getDisciplinas() {
    return disciplinas;
  }
  
  public void setDisciplinas(List<Disciplina> disciplinas) {
    this.disciplinas = disciplinas;
  }
}
