package com.empresa.dao;

import com.empresa.model.Destino;
import com.empresa.model.PaisTotal;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DestinoDAO {

    @Inject
    AgroalDataSource dataSource;

    private Destino mapear(ResultSet rs) throws SQLException {
        return new Destino(rs.getLong("id"), rs.getString("nome"), rs.getString("pais"), rs.getString("descricao"));
    }

    private List<Destino> consultaLista(String sql, Long param, String erroMsg) {
        List<Destino> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (param != null)
                pstmt.setLong(1, param);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next())
                    lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(erroMsg, e);
        }
        return lista;
    }

    public List<Destino> buscarTodos() {
        return consultaLista("SELECT id, nome, pais, descricao FROM destinos ORDER BY id", null,
                "Erro ao buscar destinos");
    }

    // Requisito: destinos agrupados e ordenados por país (o agrupamento é feito no resource).
    public List<Destino> buscarOrdenadosPorPais() {
        return consultaLista("SELECT id, nome, pais, descricao FROM destinos ORDER BY pais, nome", null,
                "Erro ao buscar destinos por país");
    }

    // Requisito: todos os destinos visitados por um certo cliente.
    public List<Destino> buscarPorCliente(Long clienteId) {
        return consultaLista("""
                SELECT DISTINCT d.id, d.nome, d.pais, d.descricao
                FROM destinos d
                JOIN viagens v ON v.destino_id = d.id
                WHERE v.cliente_id = ?
                ORDER BY d.nome""", clienteId, "Erro ao buscar destinos do cliente");
    }

    public Destino buscarPorId(Long id) {
        List<Destino> r = consultaLista("SELECT id, nome, pais, descricao FROM destinos WHERE id = ?", id,
                "Erro ao buscar destino por ID");
        return r.isEmpty() ? null : r.get(0);
    }

    public Destino criar(Destino d) {
        String sql = "INSERT INTO destinos (nome, pais, descricao) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, d.nome());
            pstmt.setString(2, d.pais());
            pstmt.setString(3, d.descricao());
            pstmt.executeUpdate();
            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next())
                    return new Destino(keys.getLong(1), d.nome(), d.pais(), d.descricao());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir destino", e);
        }
        return d;
    }

    public boolean atualizar(Destino d) {
        String sql = "UPDATE destinos SET nome = ?, pais = ?, descricao = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, d.nome());
            pstmt.setString(2, d.pais());
            pstmt.setString(3, d.descricao());
            pstmt.setLong(4, d.id());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar destino", e);
        }
    }

    public boolean deletar(Long id) {
        String sql = "DELETE FROM destinos WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar destino", e);
        }
    }

    // Requisito: países com MAIS de n destinos turísticos.
    public List<PaisTotal> paisesComMaisDe(int n) {
        List<PaisTotal> lista = new ArrayList<>();
        String sql = """
                SELECT pais, COUNT(*) AS total
                FROM destinos
                GROUP BY pais
                HAVING COUNT(*) > ?
                ORDER BY total DESC, pais""";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, n);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next())
                    lista.add(new PaisTotal(rs.getString("pais"), rs.getInt("total")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar países", e);
        }
        return lista;
    }
}
