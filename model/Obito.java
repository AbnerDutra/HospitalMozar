package model;

public class Obito {
    private String cpfPaciente;
    private String dataObito;

    public Obito(String cpfPaciente, String dataObito) {
        this.cpfPaciente = cpfPaciente;
        this.dataObito = dataObito;
    }

    public String getCpfPaciente() { return cpfPaciente; }
    public String getDataObito() { return dataObito; }

    @Override
    public String toString() {
        return "CPF: " + cpfPaciente + ", Data do óbito: " + dataObito;
    }
}
