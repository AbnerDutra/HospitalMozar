package service;

import model.Obito;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObitoService {
    private Connection conn;

    public ObitoService() {
        conn = ConexaoSQLite.conectar();
        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() {
        String sql = """
            CREATE TABLE IF NOT EXISTS obitos (
                cpf TEXT PRIMARY KEY,
                data TEXT,
                nome TEXT
            );
        """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela de óbitos: " + e.getMessage());
        }
    }

    public boolean inserirObito(Obito obito, Pacienteservice pacienteService) {
        if (!pacienteService.existe(obito.getCpfPaciente())) {
            return false;
        }

        String sql = "INSERT OR REPLACE INTO obitos (cpf, data, nome) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obito.getCpfPaciente());
            stmt.setString(2, obito.getDataObito());
            stmt.setString(3, obito.getnomeobito());
            stmt.executeUpdate();
            System.out.println("Óbito registrado no banco.");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao registrar óbito: " + e.getMessage());
            return false;
        }
    }

    public List<Obito> getObitos() {
        List<Obito> lista = new ArrayList<>();
        String sql = "SELECT * FROM obitos";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Obito obito = new Obito(
                    rs.getString("cpf"),
                    rs.getString("data"),
                    rs.getString("nome")
                );
                lista.add(obito);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar óbitos: " + e.getMessage());
        }
        return lista;
    }
}
