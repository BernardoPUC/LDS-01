import java.util.Map;

public class Curso {
  private String nome;
  private int creditos;
  private String curriculoAtual;
  private Map<String, Curriculo> curriculos;

  public Curso() {}

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
