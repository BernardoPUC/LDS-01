package com.projeto;

import java.util.List;

public class Professor extends Usuario {
  private List<Disciplina> disciplinas;

  public Professor(String nome, String login, String email, String senha) {
    super(nome, login, email, senha);
  }

  public void AdicionarDisciplina(Disciplina disciplina) {
    this.disciplinas.add(disciplina);
  }

  public void RemoverDisciplina(Disciplina disciplina) {
    this.disciplinas.remove(disciplina);
  }

  public List<Disciplina> ListarDisciplinas() {
    return this.disciplinas;
  }

  public void ListarAlunos() {
    this.disciplinas.stream().forEach(disciplina -> {
      this.ListarAlunos(disciplina);
    });
  }

  public void ListarAlunos(Disciplina disciplina) {
    List<Aluno> alunos = disciplina.ListarAlunos();

    alunos.stream().forEach(System.out::println);
  }
}
