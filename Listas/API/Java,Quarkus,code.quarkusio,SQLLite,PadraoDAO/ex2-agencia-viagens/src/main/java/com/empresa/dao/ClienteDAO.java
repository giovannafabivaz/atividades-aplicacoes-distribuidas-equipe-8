package com.empresa.dao;

import com.empresa.model.Cliente;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClienteDAO {

    @Inject
    AgroalDataSource dataSource;

    private Cliente mapear(ResultSet rs) throws SQLException {
        return new Cliente(rs.getLong("id"), rs.getString("nome"), rs.getString("email"));
    }

    public List<Cliente> buscarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id, nome, email FROM clientes ORDER BY id";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next())
                lista.add(mapear(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar clientes", e);
        }
        return lista;
    }

    public Cliente buscarPorId(Long id) {
        String sql = "SELECT id, nome, email FROM clientes WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next())
                    return mapear(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID", e);
        }
        return null;
    }

    public Cliente criar(Cliente c) {
        String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, c.nome());
            pstmt.setString(2, c.email());
            pstmt.executeUpdate();
            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next())
                    return new Cliente(keys.getLong(1), c.nome(), c.email());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente", e);
        }
        return c;
    }

    public boolean atualizar(Cliente c) {
        String sql = "UPDATE clientes SET nome = ?, email = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.nome());
            pstmt.setString(2, c.email());
            pstmt.setLong(3, c.id());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente", e);
        }
    }

    public boolean deletar(Long id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente", e);
        }
    }

    // Requisito: todos os clientes que visitaram um certo destino.
    public List<Cliente> buscarPorDestino(Long destinoId) {
        List<Cliente> lista = new ArrayList<>();
        String sql = """
                SELECT DISTINCT c.id, c.nome, c.email
                FROM clientes c
                JOIN viagens v ON v.cliente_id = c.id
                WHERE v.destino_id = ?
                ORDER BY c.nome""";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, destinoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next())
                    lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar clientes do destino", e);
        }
        return lista;
    }
}
