import model.Paciente;
import model.Obito;
import service.Pacienteservice;
import service.ObitoService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class PacienteGUI extends JFrame {
    private Pacienteservice pacienteService = new Pacienteservice();
    private ObitoService obitoService = new ObitoService();

    public PacienteGUI() {
        setTitle("Sistema de Pacientes");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criar painel principal com GridLayout e espaçamento entre botões
        JPanel painelBotoes = new JPanel(new GridLayout(8, 1, 10, 10));
        // Adicionar borda para afastar dos cantos da janela
        painelBotoes.setBorder(new EmptyBorder(20, 20, 20, 20)); // top, left, bottom, right

        JButton btnInserir = new JButton("Inserir paciente");
        JButton btnRemover = new JButton("Remover paciente");
        JButton btnListar = new JButton("Listar pacientes");
        JButton btnFiltrar = new JButton("Filtrar pacientes");
        JButton btnAlterar = new JButton("Alterar paciente");
        JButton btnRegistrarObito = new JButton("Registrar óbito");
        JButton btnListarObitos = new JButton("Listar óbitos");
        JButton btnSair = new JButton("Sair");

        painelBotoes.add(btnInserir);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnFiltrar);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnRegistrarObito);
        painelBotoes.add(btnListarObitos);
        painelBotoes.add(btnSair);

        // Adiciona o painel com os botões à janela
        setContentPane(painelBotoes);

        ImageIcon icon = new ImageIcon("logo.jfif");
        setIconImage(icon.getImage());

        // Configurar listeners dos botões
        btnInserir.addActionListener(_ -> inserirPaciente());
        btnRemover.addActionListener(_ -> removerPaciente());
        btnListar.addActionListener(_ -> listarPacientes());
        btnFiltrar.addActionListener(_ -> filtrarPacientes());
        btnAlterar.addActionListener(_ -> alterarPaciente());
        btnRegistrarObito.addActionListener(_ -> registrarObito());
        btnListarObitos.addActionListener(_ -> listarObitos());
        btnSair.addActionListener(_ -> System.exit(0));

        setVisible(true);
    }

    private void inserirPaciente() {
        String nome = JOptionPane.showInputDialog(this, "Nome:");
        String cpf = JOptionPane.showInputDialog(this, "CPF:");
        String dataNasc = JOptionPane.showInputDialog(this, "Data de nascimento:");
        int idade = Integer.parseInt(JOptionPane.showInputDialog(this, "Idade:"));
        String estado = JOptionPane.showInputDialog(this, "Estado:");
        String cidade = JOptionPane.showInputDialog(this, "Cidade:");

        Paciente p = new Paciente(nome, cpf, dataNasc, idade, estado, cidade);
        pacienteService.inserirPaciente(p);
        JOptionPane.showMessageDialog(this, "Paciente inserido com sucesso!");
    }

    private void removerPaciente() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do paciente a remover:");
        pacienteService.removerPaciente(cpf);
        JOptionPane.showMessageDialog(this, "Se o paciente existia, ele foi removido.");
    }

    private void listarPacientes() {
        List<Paciente> pacientes = pacienteService.getPacientes();
        if (pacientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum paciente cadastrado.");
        } else {
            StringBuilder sb = new StringBuilder();
            pacientes.forEach(p -> sb.append(p).append("\n"));
            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(area), "Lista de Pacientes", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void filtrarPacientes() {
        String[] opcoes = {
            "1 - Por nome",
            "2 - Por CPF",
            "3 - Por estado",
            "4 - Por cidade",
            "5 - Por faixa etária"
        };

        String opcao = (String) JOptionPane.showInputDialog(
            this,
            "Escolha o critério de filtro:",
            "Filtrar Pacientes",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );

        if (opcao == null) return;

        List<Paciente> filtrados = null;

        switch (opcao.charAt(0)) {
            case '1':
                String nome = JOptionPane.showInputDialog(this, "Digite o nome (ou parte):");
                filtrados = pacienteService.getPacientes().stream()
                    .filter(p -> p.getNome().toLowerCase().contains(nome.toLowerCase()))
                    .collect(Collectors.toList());
                break;
            case '2':
                String cpf = JOptionPane.showInputDialog(this, "Digite o CPF:");
                filtrados = pacienteService.getPacientes().stream()
                    .filter(p -> p.getCpf().equals(cpf))
                    .collect(Collectors.toList());
                break;
            case '3':
                String estado = JOptionPane.showInputDialog(this, "Digite o estado:");
                filtrados = pacienteService.getPacientes().stream()
                    .filter(p -> p.getEstado().equalsIgnoreCase(estado))
                    .collect(Collectors.toList());
                break;
            case '4':
                String cidade = JOptionPane.showInputDialog(this, "Digite a cidade:");
                filtrados = pacienteService.getPacientes().stream()
                    .filter(p -> p.getCidade().equalsIgnoreCase(cidade))
                    .collect(Collectors.toList());
                break;
            case '5':
                int min = Integer.parseInt(JOptionPane.showInputDialog(this, "Idade mínima:"));
                int max = Integer.parseInt(JOptionPane.showInputDialog(this, "Idade máxima:"));
                filtrados = pacienteService.getPacientes().stream()
                    .filter(p -> p.getIdade() >= min && p.getIdade() <= max)
                    .collect(Collectors.toList());
                break;
            default:
                JOptionPane.showMessageDialog(this, "Opção inválida.");
                return;
        }

        if (filtrados == null || filtrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum paciente encontrado.");
        } else {
            StringBuilder sb = new StringBuilder();
            filtrados.forEach(p -> sb.append(p).append("\n"));
            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(area), "Pacientes Filtrados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void alterarPaciente() {
        String cpf = JOptionPane.showInputDialog(this, "Digite o CPF do paciente:");
        Paciente p = pacienteService.buscarPorCpf(cpf);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Paciente não encontrado.");
            return;
        }

        String novoNome = JOptionPane.showInputDialog(this, "Novo nome:", p.getNome());
        String novaDataNasc = JOptionPane.showInputDialog(this, "Nova data de nascimento:", p.getDataNasc());
        int novaIdade = Integer.parseInt(JOptionPane.showInputDialog(this, "Nova idade:", p.getIdade()));
        String novoEstado = JOptionPane.showInputDialog(this, "Novo estado:", p.getEstado());
        String novaCidade = JOptionPane.showInputDialog(this, "Nova cidade:", p.getCidade());

        p.setNome(novoNome);
        p.setDataNasc(novaDataNasc);
        p.setIdade(novaIdade);
        p.setEstado(novoEstado);
        p.setCidade(novaCidade);

        JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso!");
    }

    private void registrarObito() {
        String nomeobito = JOptionPane.showInputDialog(this, "Digite o nome do paciente falecido:");
        String cpfPaciente = JOptionPane.showInputDialog(this, "Digite o CPF do paciente falecido:");
        String dataObito = JOptionPane.showInputDialog(this, "Digite a data do óbito:");

        Obito obito = new Obito(cpfPaciente, dataObito, nomeobito);
        obitoService.inserirObito(obito, pacienteService);
        JOptionPane.showMessageDialog(this, "Óbito registrado.");
    }

    private void listarObitos() {
        List<Obito> lista = obitoService.getObitos();

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum óbito registrado.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Obito o : lista) {
            sb.append("Nome: ").append(o.getnomeobito()).append("\n");
            sb.append("CPF: ").append(o.getCpfPaciente()).append("\n");
            sb.append("Data do óbito: ").append(o.getDataObito()).append("\n");
            sb.append("------------------------\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString(), "Lista de Óbitos", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new PacienteGUI();
    }
}
