import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    // Nomes dos arquivos binários
    static String arqProfessores = "professores.bin";
    static String arqAlunos = "alunos.bin";
    static String arqCursos = "cursos.bin";
    static String arqDisciplinas = "disciplinas.bin";

    // #region utilidades

    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Menu
     * 
     * @param teclado Scanner de leitura
     * @return Opção do usuário (int)
     */

    private static int menu(Scanner teclado) {
        // limparTela();
        System.out.println("\033[1;32mSistema de Matrículas");
        System.out.println("=================================================");
        System.out.println("1 - Realizar Login");
        System.out.println("0 - Sair");
        int opcao = 0;
        try {
            opcao = teclado.nextInt();
            teclado.nextLine();
        } catch (InputMismatchException ex) {
            teclado.nextLine();
            System.out.println("\033[1;31mSomente opções numéricas!");
            opcao = -1;
        }
        return opcao;
    }

    private static void menuAluno(Scanner teclado, Usuario usuario) {
        limparTela();
        System.out.println("========================================");
        System.out.println("== 1 - Fazer matricula em matéria     ==");
        System.out.println("== 2 - Cancelar matricula em matéria  ==");
        System.out.println("== 3 - Confirmar matricula            ==");
        System.out.println("== 4 - Pagar cobranca                 ==");
        System.out.println("== 0 - Sair do sistema                ==");
        System.out.println("========================================");

        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            switch (opcao) {
                case 1:
                    AlunoFactory.Matricular(teclado, usuario);
                    break;
                case 2:
                    AlunoFactory.CancelarMatricula(teclado, usuario);
                    break;
                case 3:
                    AlunoFactory.ConfirmarMatricula(teclado, usuario);
                case 4:
                    AlunoFactory.PagarCobranca(teclado, usuario);
                    break;
                default:
                    System.out.println("\033[1;31mOpção inválida!");
                    break;
            }
        } catch (InputMismatchException ex) {
            teclado.nextLine();
            System.out.println("\033[1;31mSomente opções numéricas.");
        }
    }

    private static void menuProfessor(Scanner teclado, Usuario usuario) {
        limparTela();
        System.out.println("========================================");
        System.out.println("== 1 - Listar alunos                  ==");
        System.out.println("== 0 - Sair do sistema                ==");
        System.out.println("========================================");

        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            switch (opcao) {
                case 1:
                    ProfessorFactory.ListarAlunos(teclado, usuario);
                    break;
                default:
                    System.out.println("\033[1;31mOpção inválida!");
                    break;
            }
        } catch (InputMismatchException ex) {
            teclado.nextLine();
            System.out.println("\033[1;31mSomente opções numéricas.");
        }
    }

    private static void menuSecretaria(Scanner teclado, Coordenacao secretaria) {
        int opcao = 0;

        do {
            limparTela();
            System.out.println("========================================");
            System.out.println("== 1 - Cadastrar aluno                ==");
            System.out.println("== 2 - Cadastrar professor            ==");
            System.out.println("== 3 - Cadastrar curso                ==");
            System.out.println("== 4 - Cadastrar Disciplina           ==");
            System.out.println("== 5 - Gerar curriculo                ==");
            System.out.println("== 0 - Sair do sistema                ==");
            System.out.println("========================================");

            try {
                opcao = teclado.nextInt();
                teclado.nextLine();
                switch (opcao) {
                    case 1:
                        CoordenacaoFactory.CadastrarAluno(teclado);
                        break;
                    case 2:
                        CoordenacaoFactory.CadastrarProfessor(teclado);
                        break;
                    case 3:
                        CoordenacaoFactory.CadastrarCurso(teclado);
                        break;
    
                        // System.out.println("Digite o nome do usuario: ");
                        // String nomeUsuario = teclado.nextLine();
    
                        // System.out.println("Digite o login do usuario: ");
                        // String loginUsuario = teclado.nextLine();
    
                        // System.out.println("Digite o email do usuario: ");
                        // String emailUsuario = teclado.nextLine();
    
                        // System.out.println("Digite a senha do usuario: ");
                        // String senhaUsuario = teclado.nextLine();
    
                        // // Cria o objeto com base no tipo de usuario
                        // System.out.println("Selecione o tipo do usuário:");
                        // System.out.println("=================================================");
                        // System.out.println("1 - Professor");
                        // System.out.println("2 - Aluno");
    
                        // try {
                        //     int tipoUsuario = teclado.nextInt();
                        //     teclado.nextLine();
                        //     switch (tipoUsuario) {
                        //         case 1:
                        //             Professor usuarioProfessor = new Professor(nomeUsuario, loginUsuario, emailUsuario,
                        //                     senhaUsuario);
                        //             secretaria.addProfessor(usuarioProfessor);
                        //             break;
    
                        //         case 2:
                        //             // Lista e seleciona os cursos
                        //             ArrayList<Object> cursos = secretaria.getCursos();
                        //             System.out.println("Digite o nome do curso: (Opções abaixo)");
                        //             cursos.forEach((c) -> System.out.println(c.toString()));
    
                        //             String nomeCurso = teclado.nextLine();
                        //             // Filtra o Arraylist de cursos pelo nome e retorna o primeiro elemento
                        //             Curso curso = (Curso) cursos.stream()
                        //                     .filter((c) -> ((Curso) c)
                        //                             .getNome()
                        //                             .equals(nomeCurso))
                        //                     .findFirst()
                        //                     .orElse(null);
    
                        //             if (curso != null) {
                        //                 System.out.println("\033[1;32mCurso selecionado!");
                        //                 System.out.println(curso.toString());
                        //                 Aluno usuarioAluno = new Aluno(nomeUsuario, loginUsuario, emailUsuario,
                        //                         senhaUsuario, curso);
                        //                 secretaria.addAluno(usuarioAluno);
                        //             } else
                        //                 System.out.println("\033[1;31mCurso não encontrado!");
                        //             break;
    
                        //         default:
                        //             System.out.println("\033[1;31mOpção inválida!");
                        //             break;
                        //     }
                        // } catch (InputMismatchException ex) {
                        //     teclado.nextLine();
                        //     System.out.println("\033[1;31mSomente opções numéricas.");
                        // }
                        // break;
    
                    // case 2:
                    //     System.out.println("Digite o nome do curso: ");
                    //     String nomeCurso = teclado.nextLine();
    
                    //     System.out.println("Digite a quantidade de créditos do curso: ");
                    //     int creditosCurso = teclado.nextInt();
    
                    //     Curso curso = new Curso(nomeCurso, creditosCurso);
                    //     secretaria.addCurso(curso);
                    //     break;
    
                    // case 3:
                    //     System.out.println("Digite o nome da disciplina: ");
                    //     String nomeDisciplina = teclado.nextLine();
    
                    //     // Lista e seleciona os professores
                    //     ArrayList<Object> professores = secretaria.getProfessores();
                    //     System.out.println("Digite o nome do professor: (Opções abaixo)");
                    //     professores.forEach((c) -> System.out.println(c.toString()));
    
                    //     String nomeProfessor = teclado.nextLine();
                    //     // Filtra o Arraylist de professores pelo nome e retorna o primeiro elemento
                    //     Professor professor = (Professor) professores.stream()
                    //             .filter((p) -> ((Professor) p)
                    //                     .getNome()
                    //                     .equals(nomeProfessor))
                    //             .findFirst()
                    //             .orElse(null);
    
                    //     if (professor != null) {
                    //         System.out.println("\033[1;32mCurso selecionado!");
                    //         System.out.println(professor.toString());
    
                    //         // Retorna o ENUM com oque foi selecionado
                    //         System.out.println("Selecione o tipo da disciplina:");
                    //         System.out.println("=================================================");
                    //         System.out.println("1 - Obrigatória");
                    //         System.out.println("2 - Optativa");
                    //         int tipoDisciplinaOpcao = teclado.nextInt();
                    //         teclado.nextLine();
    
                    //         TipoDisciplina tipoDisciplina;
                    //         switch (tipoDisciplinaOpcao) {
                    //             case 1:
                    //                 tipoDisciplina = TipoDisciplina.OBRIGATORIA;
                    //                 break;
                    //             case 2:
                    //                 tipoDisciplina = TipoDisciplina.OPTATIVA;
                    //                 break;
                    //             default:
                    //                 tipoDisciplina = null;
                    //                 System.out.println("\033[1;31mOpção inválida!");
                    //                 break;
                    //         }
    
                    //         if (tipoDisciplina != null) {
                    //             // Lista e seleciona os cursos
                    //             ArrayList<Object> cursos = secretaria.getCursos();
                    //             System.out.println("Digite o nome do curso: (Opções abaixo)");
                    //             cursos.forEach((c) -> System.out.println(c.toString()));
    
                    //             String nomeCursoDisciplina = teclado.nextLine();
                    //             // Filtra o Arraylist de cursos pelo nome e retorna o primeiro elemento
                    //             Curso cursoDisciplina = (Curso) cursos.stream()
                    //                     .filter((c) -> ((Curso) c)
                    //                             .getNome()
                    //                             .equals(nomeCursoDisciplina))
                    //                     .findFirst()
                    //                     .orElse(null);
    
                    //             if (cursoDisciplina != null) {
                    //                 System.out.println("\033[1;32mCurso selecionado!");
                    //                 System.out.println(cursoDisciplina.toString());
    
                    //                 System.out.println("Digite o valor da disciplina: ");
                    //                 Double valorDisciplina = teclado.nextDouble();
    
                    //                 Disciplina disciplina = new Disciplina(nomeDisciplina, professor, tipoDisciplina,
                    //                         cursoDisciplina,
                    //                         valorDisciplina);
                    //                 secretaria.addDisciplina(disciplina);
    
                    //                 professor.AdicionarDisciplina(disciplina);
                    //             } else
                    //                 System.out.println("\033[1;31mCurso não encontrado!");
                    //         }
                    //     } else
                    //         System.out.println("\033[1;31mProfessor não encontrado!");
                    //     break;
    
                    case 4:
                        break;
    
                    case 0:
                        break;
    
                    default:
                        System.out.println("\033[1;31mOpção inválida!");
                        break;
                }
            } catch (InputMismatchException ex) {
                teclado.nextLine();
                System.out.println("\033[1;31mSomente opções numéricas.");
            }
        } while (opcao != 0);
    }

    /**
     * Pausa para leitura de mensagens em console
     * 
     * @param teclado Scanner de leitura
     */
    private static void pausa(Scanner teclado) {
        System.out.println("\033[1;32mEnter para continuar.");
        teclado.nextLine();
    }

    /**
     * Autenticação e direcionamento do usuário
     * 
     * @param teclado
     * @param usuario
     */
    private static void realizarLogin(int opcao, Scanner teclado, Coordenacao coordenacao) {
        Usuario usuarioEncontrado = AuthFactory.Autenticar(teclado, coordenacao);

        if (usuarioEncontrado != null) {
            String tipoUsuario = usuarioEncontrado.getClass().getName();
            System.out.println(tipoUsuario);

            switch (tipoUsuario) {
                case "Aluno":
                    App.menuAluno(teclado, usuarioEncontrado);
                    break;
                case "Professor":
                    App.menuProfessor(teclado, usuarioEncontrado);
                case "Coordenacao":
                    App.menuSecretaria(teclado, coordenacao);
                default:
                    break;
            }
        }
    }

    // #endregion

    /**
     * Salva os objetos em formato serializado (Object)
     * 
     * @param lista Lista com os objetos
     * @param arq   Nome do arquivo a ser gerado
     */
    public static void salvarBinario(List<Object> lista, String arq) {
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
    public static List<Object> carregarBinario(String arq) {
        List<Object> lista = null;

        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arq));
            lista = (List<Object>) entrada.readObject();
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

    public static void main(String[] args) throws Exception {
        /**
         * Criação da classe Secretaria e alimentação das classes/carregamento dos
         * arquivos binários caso existam.
         */
        Coordenacao coordenacao = new Coordenacao("secretaria", "secretaria", "secretaria@gmail.com", "123");

        Secretaria.InicializarDados(App.carregarBinario(arqProfessores), App.carregarBinario(arqAlunos), App.carregarBinario(arqCursos), App.carregarBinario(arqDisciplinas));

        /**
         * Menu
         */
        Scanner teclado = new Scanner(System.in);
        int opcao;

        while (true) {
            opcao = menu(teclado);
            if (opcao == 0) {
                limparTela();
                break;
            }

            switch (opcao) {
                case 1:
                    realizarLogin(opcao, teclado, coordenacao);
                    break;
                default:
                    break;
            }

            pausa(teclado);
            limparTela();
        }

        /**
         * Após o fim da aplicação salva os dados de usuários e disciplinas
         *
         */
        Secretaria secretaria = Secretaria.getInstance();

        App.salvarBinario(Arrays.asList(secretaria.ListarProfessores().toArray()), arqProfessores);
        App.salvarBinario(Arrays.asList(secretaria.ListarAlunos().toArray()), arqAlunos);
        App.salvarBinario(Arrays.asList(secretaria.ListarDisciplinas().toArray()), arqDisciplinas);
        App.salvarBinario(Arrays.asList(secretaria.ListarCursos().toArray()), arqCursos);
    }
}
