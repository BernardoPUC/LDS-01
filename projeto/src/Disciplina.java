import java.util.List;

public class Disciplina {
  private static final int LIMITE_ALUNOS = 60;
  private static final int MIN_ALUNOS = 3;

  private String codigo;
  private TipoDisciplina tipo;
  private Boolean ativa;
  private Double valor;
  private List<Aluno> alunos;

  public void Matricular(Aluno aluno) {}

  public void CancelarMatricula(Aluno aluno) {}

  public List<Aluno> ListarAlunos() {
    return this.alunos;
  }

  public String getCodigo() {
    return codigo;
  }
  
  public void setCodigo(String codigo) {
    this.codigo = codigo;
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

}
