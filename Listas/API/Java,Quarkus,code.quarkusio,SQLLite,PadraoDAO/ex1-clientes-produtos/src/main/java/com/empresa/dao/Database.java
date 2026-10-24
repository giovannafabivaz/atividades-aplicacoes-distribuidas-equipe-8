package com.empresa.dao;

import io.agroal.api.AgroalDataSource;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@ApplicationScoped
public class Database {

    @Inject
    AgroalDataSource dataSource;

    // Cria as tabelas quando a aplicação inicia (se ainda não existirem).
    void onStart(@Observes StartupEvent ev) {
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS clientes (
                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                      nome TEXT NOT NULL,
                      email TEXT NOT NULL)""");
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS produtos (
                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                      nome TEXT NOT NULL,
                      preco REAL NOT NULL,
                      categoria TEXT NOT NULL)""");
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS compras (
                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                      cliente_id INTEGER NOT NULL REFERENCES clientes(id) ON DELETE CASCADE,
                      produto_id INTEGER NOT NULL REFERENCES produtos(id) ON DELETE CASCADE,
                      quantidade INTEGER NOT NULL,
                      data_compra TEXT NOT NULL)""");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar o banco SQLite", e);
        }
    }
}
