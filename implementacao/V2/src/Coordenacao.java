import java.util.ArrayList;

public class Coordenacao extends Usuario {
    private ArrayList<Object> professores;
    private ArrayList<Object> alunos;
    private ArrayList<Object> cursos;
    private ArrayList<Object> disciplinas;

    public Coordenacao(String nome, String login, String email, String senha) {
        super(nome, login, email, senha);
        this.professores = new ArrayList<Object>();
        this.alunos = new ArrayList<Object>();
        this.cursos = new ArrayList<Object>();
        this.disciplinas = new ArrayList<Object>();
    }

    /**
     * Getters
     */
    public ArrayList<Object> getProfessores() {
        return this.professores;
    }

    public ArrayList<Object> getAlunos() {
        return this.alunos;
    }

    public ArrayList<Object> getDisciplinas() {
        return this.disciplinas;
    }

    public ArrayList<Object> getCursos() {
        return this.cursos;
    }

    /**
     * Setters
     */
    public void setProfessores(ArrayList<Object> professores) {
        this.professores = professores;
    }

    public void setAlunos(ArrayList<Object> alunos) {
        this.alunos = alunos;
    }

    public void setDisciplinas(ArrayList<Object> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void setCursos(ArrayList<Object> cursos) {
        this.cursos = cursos;
    }

    /**
     * Add
     */
    public void addProfessor(Professor professor) {
        this.professores.add(professor);
    }

    public void addAluno(Usuario aluno) {
        this.alunos.add(aluno);
    }

    public void addDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public void addCurso(Curso curso) {
        this.cursos.add(curso);
    }
}
