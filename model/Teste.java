package model;

public class Teste {
    private String dataTeste;
    private String cpfPaciente;
    private String resultado;

    public Teste(String dataTeste, String cpfPaciente, String resultado) {
        this.dataTeste = dataTeste;
        this.cpfPaciente = cpfPaciente;
        this.resultado = resultado.toLowerCase();
    }

    @Override
    public String toString() {
        return "Teste{" +
                "dataTeste='" + dataTeste + '\'' +
                ", cpfPaciente='" + cpfPaciente + '\'' +
                ", resultado='" + resultado + '\'' +
                '}';
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }
}
