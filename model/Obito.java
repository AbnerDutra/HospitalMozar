package model;

public class Obito {
    private String cpfPaciente;
    private String dataObito;
    private String nomeobito;

    public Obito(String nomeobito, String cpfPaciente, String dataObito) {
        this.cpfPaciente = cpfPaciente;
        this.dataObito = dataObito;
        this.nomeobito = nomeobito;
    }

    public String getnomeobito() { return nomeobito; }
    public String getCpfPaciente() { return cpfPaciente; }
    public String getDataObito() { return dataObito; }

    @Override
    public String toString() {
        return "CPF: " + cpfPaciente + ", Data do óbito: " + dataObito;
    }
}