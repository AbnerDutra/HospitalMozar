package model;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FiltroPacientes {

    public static void filtrar(List<Paciente> pacientes, Scanner sc) {
        System.out.println("\n--- FILTRAR PACIENTES ---");
        System.out.println("1. Por nome");
        System.out.println("2. Por CPF");
        System.out.println("3. Por estado");
        System.out.println("4. Por cidade");
        System.out.println("5. Por faixa etária");
        System.out.print("Escolha uma opção: ");
        String opcao = sc.nextLine();

        List<Paciente> filtrados = null;

        switch (opcao) {
            case "1":
                System.out.print("Digite o nome (ou parte dele): ");
                String nome = sc.nextLine().toLowerCase();
                filtrados = pacientes.stream()
                        .filter(p -> p.getNome().toLowerCase().contains(nome))
                        .collect(Collectors.toList());
                break;
            case "2":
                System.out.print("Digite o CPF: ");
                String cpf = sc.nextLine();
                filtrados = pacientes.stream()
                        .filter(p -> p.getCpf().equals(cpf))
                        .collect(Collectors.toList());
                break;
            case "3":
                System.out.print("Digite o estado: ");
                String estado = sc.nextLine().toLowerCase();
                filtrados = pacientes.stream()
                        .filter(p -> p.getEstado().toLowerCase().equals(estado))
                        .collect(Collectors.toList());
                break;
            case "4":
                System.out.print("Digite a cidade: ");
                String cidade = sc.nextLine().toLowerCase();
                filtrados = pacientes.stream()
                        .filter(p -> p.getCidade().toLowerCase().equals(cidade))
                        .collect(Collectors.toList());
                break;
            case "5":
                System.out.print("Idade mínima: ");
                int min = Integer.parseInt(sc.nextLine());
                System.out.print("Idade máxima: ");
                int max = Integer.parseInt(sc.nextLine());
                filtrados = pacientes.stream()
                        .filter(p -> p.getIdade() >= min && p.getIdade() <= max)
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        if (filtrados == null || filtrados.isEmpty()) {
            System.out.println("Nenhum paciente encontrado.");
        } else {
            filtrados.forEach(System.out::println);
        }
    }
}

