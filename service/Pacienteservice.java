package service;

import model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pacienteservice {

    private Connection conn;

    public Pacienteservice() {
        conn = ConexaoSQLite.conectar();
        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() {
        String sql = """
            CREATE TABLE IF NOT EXISTS pacientes (
                cpf TEXT PRIMARY KEY,
                nome TEXT,
                nascimento TEXT,
                idade INTEGER,
                estado TEXT,
                cidade TEXT
            );
        """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void inserirPaciente(Paciente p) {
       String sql = "INSERT OR REPLACE INTO pacientes (cpf, nome, nascimento, idade, estado, cidade) VALUES (?, ?, ?, ?, ?, ?)";

try (PreparedStatement stmt = conn.prepareStatement(sql)) {
    stmt.setString(1, p.getCpf());
    stmt.setString(2, p.getNome());
    stmt.setString(3, p.getNascimento());
    stmt.setInt(4, p.getIdade());
    stmt.setString(5, p.getEstado());
    stmt.setString(6, p.getCidade());

    stmt.executeUpdate();
    System.out.println("Paciente inserido no banco com sucesso.");
} catch (SQLException e) {
    System.out.println("Erro ao inserir paciente: " + e.getMessage());
}

    }

    public void removerPaciente(String cpf) {
        String sql = "DELETE FROM pacientes WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Paciente removido.");
            } else {
                System.out.println("Paciente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover paciente: " + e.getMessage());
        }
    }

    public List<Paciente> getPacientes() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Paciente p = new Paciente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("nascimento"),
                    rs.getInt("idade"),
                    rs.getString("estado"),
                    rs.getString("cidade")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pacientes: " + e.getMessage());
        }
        return lista;
    }

    public boolean existe(String cpf) {
        String sql = "SELECT COUNT(*) FROM pacientes WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao verificar existência: " + e.getMessage());
            return false;
        }
    }

    public Paciente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM pacientes WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Paciente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("nascimento"),
                    rs.getInt("idade"),
                    rs.getString("estado"),
                    rs.getString("cidade")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        }
        return null;
    }
}
