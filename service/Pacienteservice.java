package service;

import model.Paciente;
import java.util.*;

public class Pacienteservice {
    private Map<String, Paciente> pacientes = new HashMap<>();

    public void inserirPaciente(Paciente p) {
        pacientes.put(p.getCpf(), p);
        System.out.println("Paciente inserido com sucesso.");
    }

    public void removerPaciente(String cpf) {
        if (pacientes.remove(cpf) != null) {
            System.out.println("Paciente removido.");
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        for (Paciente p : pacientes.values()) {
            System.out.println(p);
        }
    }

    public void filtrarPorCidade(String cidade) {
        boolean encontrado = false;
        for (Paciente p : pacientes.values()) {
            if (p.toString().toLowerCase().contains(cidade.toLowerCase())) {
                System.out.println(p);
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("Nenhum paciente encontrado para essa cidade.");
    }
public List<Paciente> getPacientes() {
    return new ArrayList<>(pacientes.values());
}
    public void alterarPaciente(String cpf, Scanner sc) {
        Paciente p = pacientes.get(cpf);
        if (p == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        p.setNome(sc.nextLine());
        System.out.print("Nova idade: ");
        p.setIdade(Integer.parseInt(sc.nextLine()));
        System.out.print("Novo estado: ");
        p.setEstado(sc.nextLine());
        System.out.print("Nova cidade: ");
        p.setCidade(sc.nextLine());

        System.out.println("Dados atualizados.");
    }

    public boolean existe(String cpf) {
        return pacientes.containsKey(cpf);
    }

    public Paciente buscarPorCpf(String cpf) {
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorCpf'");
    }
}
