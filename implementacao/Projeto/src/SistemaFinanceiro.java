import java.util.List;

public class SistemaFinanceiro {
  private List<Cobranca> cobrancas;
  private SistemaFinanceiro sistemaFinanceiro;

  private SistemaFinanceiro() {}

  public SistemaFinanceiro GetInstance() {
    if (this.sistemaFinanceiro == null) {
      this.sistemaFinanceiro = new SistemaFinanceiro();
    }

    return this.sistemaFinanceiro;
  }

  public void PagarCobranca() {}

  public void GerarCobranca(Aluno aluno) {}

}
