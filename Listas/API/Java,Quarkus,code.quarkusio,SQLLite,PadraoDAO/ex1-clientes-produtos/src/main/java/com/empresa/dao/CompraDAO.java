package com.empresa.dao;

import com.empresa.model.Compra;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CompraDAO {

    @Inject
    AgroalDataSource dataSource;

    public Compra criar(Compra c) {
        String sql = "INSERT INTO compras (cliente_id, produto_id, quantidade, data_compra) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, c.clienteId());
            pstmt.setLong(2, c.produtoId());
            pstmt.setInt(3, c.quantidade());
            pstmt.setString(4, c.dataCompra().toString());
            pstmt.executeUpdate();
            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next())
                    return new Compra(keys.getLong(1), c.clienteId(), c.produtoId(), c.quantidade(), c.dataCompra());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar compra", e);
        }
        return c;
    }

    public List<Compra> buscarTodas() {
        List<Compra> lista = new ArrayList<>();
        String sql = "SELECT id, cliente_id, produto_id, quantidade, data_compra FROM compras ORDER BY id";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next())
                lista.add(new Compra(rs.getLong("id"), rs.getLong("cliente_id"), rs.getLong("produto_id"),
                        rs.getInt("quantidade"), LocalDate.parse(rs.getString("data_compra"))));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar compras", e);
        }
        return lista;
    }

    public boolean deletar(Long id) {
        String sql = "DELETE FROM compras WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar compra", e);
        }
    }
}
