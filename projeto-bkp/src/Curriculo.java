import java.util.List;

public class Curriculo {
  private String codigo;
  private List<Disciplina> disciplinas;

  public Curriculo() {}

  public String getCodigo() {
    return codigo;
  }
  
  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public List<Disciplina> getDisciplinas() {
    return disciplinas;
  }
  
  public void setDisciplinas(List<Disciplina> disciplinas) {
    this.disciplinas = disciplinas;
  }
}
