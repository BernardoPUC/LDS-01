import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Secretaria extends Usuario {
    private ArrayList<Object> professores;
    private ArrayList<Object> alunos;
    private ArrayList<Object> cursos;
    private ArrayList<Object> disciplinas;

    public Secretaria(String nome, String login, String email, String senha) {
        super(nome, login, email, senha);
        this.professores = new ArrayList<Object>();
        this.alunos = new ArrayList<Object>();
        this.cursos = new ArrayList<Object>();
        this.disciplinas = new ArrayList<Object>();
    }

    /**
     * Salva os objetos em formato serializado (Object)
     * 
     * @param lista Lista com os objetos
     * @param arq   Nome do arquivo a ser gerado
     */
    public void salvarBinario(ArrayList<Object> lista, String arq) {
        ObjectOutputStream saida = null;
        try {
            saida = new ObjectOutputStream(new FileOutputStream(arq));
            saida.writeObject(lista);
            saida.close();
        } catch (FileNotFoundException fe) {
            System.out.println(
                    "\033[1;31mArquivo não encontrado, ou permissão negada. Tente novamente com outro arquivo");
        } catch (IOException ex) {
            System.out.println("\033[1;31mProblemas na operação de E/S. Contacte o suporte");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Carrega os objetos de um arquivo serializado (Object)
     * 
     * @param arq Nome do arquivo de dados
     * @return Uma lista genérica com a classe do arquivo passado como parâmetro.
     *         A lista pode estar vazia ou nula em caso de exceções.
     */
    public ArrayList<Object> carregarBinario(String arq) {
        ArrayList<Object> lista = null;

        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arq));
            lista = (ArrayList<Object>) entrada.readObject();
            entrada.close();
        } catch (FileNotFoundException fe) {
            lista = new ArrayList<Object>();
        } catch (ClassNotFoundException ce) {
            System.out.println("\033[1;31mProblema na conversão dos dados: classe inválida. Contacte o suporte.");
        } catch (IOException ie) {
            System.out.println("\033[1;31mProblemas na operação de E/S. Contacte o suporte");
            System.out.println(ie.getMessage());
        }

        return lista;
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
