package com.projeto;

public class Cobranca {
  private double valor;
  private Aluno devedor;
  private Boolean ativa;
  private Boolean paga;

  public Cobranca(double valor, Aluno devedor, Boolean ativa) {
    this.setValor(valor);
    this.setDevedor(devedor);
    this.ativa = ativa;
    this.paga = false;
  }

  public void Pagar() {
    this.ativa = false;
    this.paga = true;
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

  public Boolean getPaga() {
    return paga;
  }
}
