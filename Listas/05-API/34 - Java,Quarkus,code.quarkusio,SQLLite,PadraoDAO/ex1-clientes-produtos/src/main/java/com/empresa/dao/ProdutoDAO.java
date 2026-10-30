package com.empresa.dao;

import com.empresa.model.CategoriaTotal;
import com.empresa.model.Produto;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProdutoDAO {

    @Inject
    AgroalDataSource dataSource;

    private Produto mapear(ResultSet rs) throws SQLException {
        return new Produto(rs.getLong("id"), rs.getString("nome"), rs.getDouble("preco"), rs.getString("categoria"));
    }

    public List<Produto> buscarTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT id, nome, preco, categoria FROM produtos ORDER BY id";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next())
                lista.add(mapear(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produtos", e);
        }
        return lista;
    }

    public Produto buscarPorId(Long id) {
        String sql = "SELECT id, nome, preco, categoria FROM produtos WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next())
                    return mapear(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por ID", e);
        }
        return null;
    }

    public Produto criar(Produto p) {
        String sql = "INSERT INTO produtos (nome, preco, categoria) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, p.nome());
            pstmt.setDouble(2, p.preco());
            pstmt.setString(3, p.categoria());
            pstmt.executeUpdate();
            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next())
                    return new Produto(keys.getLong(1), p.nome(), p.preco(), p.categoria());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto", e);
        }
        return p;
    }

    public boolean atualizar(Produto p) {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, categoria = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.nome());
            pstmt.setDouble(2, p.preco());
            pstmt.setString(3, p.categoria());
            pstmt.setLong(4, p.id());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    public boolean deletar(Long id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar produto", e);
        }
    }

    // Requisito: todos os produtos adquiridos por um cliente.
    public List<Produto> buscarPorCliente(Long clienteId) {
        List<Produto> lista = new ArrayList<>();
        String sql = """
                SELECT DISTINCT p.id, p.nome, p.preco, p.categoria
                FROM produtos p
                JOIN compras c ON c.produto_id = p.id
                WHERE c.cliente_id = ?
                ORDER BY p.nome""";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, clienteId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next())
                    lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produtos do cliente", e);
        }
        return lista;
    }

    // Requisito: categorias adquiridas por MAIS de n clientes distintos.
    public List<CategoriaTotal> categoriasAdquiridasPorMaisDe(int n) {
        List<CategoriaTotal> lista = new ArrayList<>();
        String sql = """
                SELECT p.categoria AS categoria, COUNT(DISTINCT c.cliente_id) AS total
                FROM compras c
                JOIN produtos p ON p.id = c.produto_id
                GROUP BY p.categoria
                HAVING COUNT(DISTINCT c.cliente_id) > ?
                ORDER BY total DESC, p.categoria""";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, n);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next())
                    lista.add(new CategoriaTotal(rs.getString("categoria"), rs.getInt("total")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar categorias", e);
        }
        return lista;
    }
}
