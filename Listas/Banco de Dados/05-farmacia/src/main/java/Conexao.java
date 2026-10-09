import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe utilitária que gerencia a conexão com o banco de dados SQLite
 * e a criação das tabelas do sistema.
 */
public class Conexao {

    /**
     * Construtor privado: a classe só possui métodos estáticos.
     */
    private Conexao() {
    }

    /** Endereço do banco de dados SQLite (arquivo farmacia.db). */
    private static final String URL = "jdbc:sqlite:farmacia.db";

    /**
     * Abre uma conexão com o banco de dados.
     * Ativa as chaves estrangeiras, que no SQLite vêm desligadas por padrão.
     *
     * @return conexão aberta
     * @throws SQLException se não for possível conectar
     */
    public static Connection conectar() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON");
        }
        return conn;
    }

    /**
     * Cria todas as tabelas do sistema, caso ainda não existam.
     * A ordem respeita as chaves estrangeiras (tabelas referenciadas primeiro).
     *
     * @throws SQLException se ocorrer erro na criação das tabelas
     */
    public static void criarTabelas() throws SQLException {
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS medicamento ("
                    + "id_medicamento INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nome TEXT NOT NULL, "
                    + "preco REAL NOT NULL)");
            stmt.execute("CREATE TABLE IF NOT EXISTS fornecedor ("
                    + "id_fornecedor INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nome TEXT NOT NULL, "
                    + "cidade TEXT NOT NULL)");
            stmt.execute("CREATE TABLE IF NOT EXISTS compra ("
                    + "id_compra INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "id_medicamento INTEGER NOT NULL, "
                    + "id_fornecedor INTEGER NOT NULL, "
                    + "quantidade INTEGER NOT NULL, "
                    + "data_compra TEXT NOT NULL, "
                    + "FOREIGN KEY (id_medicamento) REFERENCES medicamento (id_medicamento), "
                    + "FOREIGN KEY (id_fornecedor) REFERENCES fornecedor (id_fornecedor))");
        }
    }
}
