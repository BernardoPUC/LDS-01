package com.projeto;

import java.util.Map;

public class Curso {
  private static int nextId = 0;

  private int id;
  private String nome;
  private int creditos;
  private String curriculoAtual;
  private Map<String, Curriculo> curriculos;

  public Curso(String nome, int creditos) {
    this.setNome(nome);
    this.setCreditos(creditos);
    this.setId(++Curso.nextId);
  }

  public int getId() {
    return id;
  }

  private void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getCreditos() {
    return creditos;
  }

  public void setCreditos(int creditos) {
    this.creditos = creditos;
  }

  public String getCurriculoAtual() {
    return curriculoAtual;
  }
   
  public void setCurriculoAtual(String curriculoAtual) {
    this.curriculoAtual = curriculoAtual;
  }
}
