package model;

public class Paciente {
    private String nome;
    private String cpf;
    private String nascimento;
    private int idade;
    private String estado;
    private String cidade;

    public Paciente(String nome, String cpf, String dataNascimento, int idade, String estado, String cidade) {
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = dataNascimento;
        this.idade = idade;
        this.estado = estado;
        this.cidade = cidade;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNascimento() {
        return nascimento;
    }

    public int getIdade() {
        return idade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", nascimento='" + nascimento + '\'' +
                ", idade=" + idade +
                ", estado='" + estado + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }

    public void setDataNasc(String novaDataNasc) {
        throw new UnsupportedOperationException("Unimplemented method 'setDataNasc'");
    }

    public Object getDataNasc() {
        throw new UnsupportedOperationException("Unimplemented method 'getDataNasc'");
    }
}
