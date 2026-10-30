package com.empresa.dao;

import com.empresa.model.Viagem;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ViagemDAO {

    @Inject
    AgroalDataSource dataSource;

    public Viagem criar(Viagem v) {
        String sql = "INSERT INTO viagens (cliente_id, destino_id, data_visita) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, v.clienteId());
            pstmt.setLong(2, v.destinoId());
            pstmt.setString(3, v.dataVisita().toString());
            pstmt.executeUpdate();
            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next())
                    return new Viagem(keys.getLong(1), v.clienteId(), v.destinoId(), v.dataVisita());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar viagem", e);
        }
        return v;
    }

    public List<Viagem> buscarTodas() {
        List<Viagem> lista = new ArrayList<>();
        String sql = "SELECT id, cliente_id, destino_id, data_visita FROM viagens ORDER BY id";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next())
                lista.add(new Viagem(rs.getLong("id"), rs.getLong("cliente_id"), rs.getLong("destino_id"),
                        LocalDate.parse(rs.getString("data_visita"))));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar viagens", e);
        }
        return lista;
    }

    public boolean deletar(Long id) {
        String sql = "DELETE FROM viagens WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar viagem", e);
        }
    }
}
