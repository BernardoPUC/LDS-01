import java.util.Scanner;

public class App {
    private static void printMenuAluno() {
        System.out.println("========================================");
        System.out.println("== 1 - Fazer matricula em matéria     ==");
        System.out.println("== 2 - Cancelar matricula em matéria  ==");
        System.out.println("== 0 - Sair do sistema                ==");
        System.out.println("========================================");
    }

    private static void printMenuProfessor() {
        System.out.println("========================================");
        System.out.println("== 1 - Listar alunos                  ==");
        System.out.println("== 0 - Sair do sistema                ==");
        System.out.println("========================================");
    }

    private static void printMenuCoordenacao() {
        System.out.println("========================================");
        System.out.println("== 1 - Cadastrar aluno                ==");
        System.out.println("== 2 - Cadastrar professor            ==");
        System.out.println("== 3 - Cadastrar curso                ==");
        System.out.println("== 4 - Cadastrar curriculo            ==");
        System.out.println("== 0 - Sair do sistema                ==");
        System.out.println("========================================");
    }

    private static void printMenu(Usuario usuario) {
        if (usuario == null) {
            System.out.println("========================================");
            System.out.println("== 1 - Fazer login                    ==");
            System.out.println("== 0 - Sair do sistema                ==");
            System.out.println("========================================");
        } else {
            String tipoUsuario = usuario.getClass().getName();

            switch (tipoUsuario) {
                case "Aluno":
                    App.printMenuAluno();
                    break;
                case "Professor":
                    App.printMenuProfessor();
                case "Coordenacao":
                    App.printMenuCoordenacao();
                default:
                    break;
            }
        }     
    }

    private static void ProcessarInstrucao(String instrucao, Usuario usuario) {
        if (usuario == null) {
            if (instrucao.equals("1")) {
                Usuario usuarioEncontrado = AuthFactory.Autenticar(scanner);

                if (usuarioEncontrado != null) {
                    usuario = usuarioEncontrado;
                }
            }
        } else {

        }
    }

    private static void CarregarDados() {}

    private static void SalvarDados() {}
    public static void main(String[] args) throws Exception {
        Usuario usuario = null;
        Scanner scanner = new Scanner(System.in);
        String instrucao;

        App.CarregarDados();

        do {
            App.printMenu(usuario);

            instrucao = ScannerUtils.lerInstrucao("Escolha uma opção: ", scanner);

            if (!instrucao.equals("0")) {
                App.ProcessarInstrucao(instrucao, usuario);
            }
        } while (!instrucao.equals("0"));

        App.SalvarDados();
    }
}
