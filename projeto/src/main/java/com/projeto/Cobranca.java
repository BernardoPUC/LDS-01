package com.projeto;

public class Cobranca {
  private double valor;
  private Aluno devedor;
  private Boolean ativa;

  public Cobranca(double valor, Aluno devedor, Boolean ativa) {
    this.setValor(valor);
    this.setDevedor(devedor);
    this.setAtiva(ativa);
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public Aluno getDevedor() {
    return devedor;
  }

  public void setDevedor(Aluno devedor) {
    this.devedor = devedor;
  }

  public Boolean getAtiva() {
    return ativa;
  }
  
  public void setAtiva(Boolean ativa) {
    this.ativa = ativa;
  }
}
