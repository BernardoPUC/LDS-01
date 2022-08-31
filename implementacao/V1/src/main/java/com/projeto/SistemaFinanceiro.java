package com.projeto;

import java.util.List;

public class SistemaFinanceiro {
  private List<Cobranca> cobrancas;
  private static SistemaFinanceiro sistemaFinanceiro;

  private SistemaFinanceiro() {}

  public static SistemaFinanceiro getInstance() {
    if (SistemaFinanceiro.sistemaFinanceiro == null) {
      SistemaFinanceiro.sistemaFinanceiro = new SistemaFinanceiro();
    }

    return SistemaFinanceiro.sistemaFinanceiro;
  }

  public void PagarCobranca(Cobranca cobranca) {
    cobranca.Pagar();
  }

  public void GerarCobranca(Aluno aluno) {
    double valor = aluno.ListarDisciplinasMatriculadas().stream().map(d -> d.getValor()).reduce(0D, (valorTotal, valorDisciplina) -> valorTotal + valorDisciplina);

    Cobranca cobranca = new Cobranca(valor, aluno, true);

    aluno.AdicionarCobranca(cobranca);
    this.cobrancas.add(cobranca);
  }

}
