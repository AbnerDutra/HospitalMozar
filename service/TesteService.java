package service;

import model.Teste;
import java.util.ArrayList;
import java.util.List;

public class TesteService {
    private List<Teste> testes = new ArrayList<>();

    public void inserirTeste(Teste teste, Pacienteservice pacienteService) {
        if (!pacienteService.existe(teste.getCpfPaciente())) {
            System.out.println("Erro: Paciente com CPF " + teste.getCpfPaciente() + " não está cadastrado.");
            return;
        }
        testes.add(teste);
        System.out.println("Teste inserido com sucesso.");
    }

    public void listarTestes() {
        if (testes.isEmpty()) {
            System.out.println("Nenhum teste cadastrado.");
            return;
        }
        for (Teste t : testes) {
            System.out.println(t);
        }
    }
}