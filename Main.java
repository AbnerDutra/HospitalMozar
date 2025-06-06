import model.Paciente;
import service.Pacienteservice;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pacienteservice pacienteService = new Pacienteservice();
        int opcao;

        do {
            System.out.println("\n=== MENU PACIENTES ===");
            System.out.println("1 - Inserir paciente");
            System.out.println("2 - Remover paciente");
            System.out.println("3 - Listar pacientes");
            System.out.println("4 - Filtrar por cidade");
            System.out.println("5 - Alterar dados de paciente");
            System.out.println("0 - Sair");
            System.out.print("Opçao: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Data nascimento: ");
                    String dataNasc = sc.nextLine();
                    System.out.print("Idade: ");
                    int idade = Integer.parseInt(sc.nextLine());
                    System.out.print("Estado: ");
                    String estado = sc.nextLine();
                    System.out.print("Cidade: ");
                    String cidade = sc.nextLine();
                    Paciente p = new Paciente(nome, cpf, dataNasc, idade, estado, cidade);
                    pacienteService.inserirPaciente(p);
                    break;
                case 2:
                    System.out.print("CPF do paciente a remover: ");
                    pacienteService.removerPaciente(sc.nextLine());
                    break;
                case 3:
                    pacienteService.listarPacientes();
                    break;
                case 4:
                    System.out.print("Cidade: ");
                    pacienteService.filtrarPorCidade(sc.nextLine());
                    break;
                case 5:
                    System.out.print("CPF do paciente: ");
                    pacienteService.alterarPaciente(sc.nextLine(), sc);
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }
}
