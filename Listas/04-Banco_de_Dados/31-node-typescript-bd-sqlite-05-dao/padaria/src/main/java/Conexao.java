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

    /** Endereço do banco de dados SQLite (arquivo padaria.db). */
    private static final String URL = "jdbc:sqlite:padaria.db";

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
            stmt.execute("CREATE TABLE IF NOT EXISTS cliente ("
                    + "id_cliente INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nome TEXT NOT NULL)");
            stmt.execute("CREATE TABLE IF NOT EXISTS produto ("
                    + "id_produto INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nome TEXT NOT NULL, "
                    + "preco REAL NOT NULL)");
            stmt.execute("CREATE TABLE IF NOT EXISTS pedido ("
                    + "id_pedido INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "id_cliente INTEGER NOT NULL, "
                    + "data_pedido TEXT NOT NULL, "
                    + "total REAL NOT NULL, "
                    + "FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente))");
            stmt.execute("CREATE TABLE IF NOT EXISTS item_pedido ("
                    + "id_pedido INTEGER NOT NULL, "
                    + "id_produto INTEGER NOT NULL, "
                    + "quantidade INTEGER NOT NULL, "
                    + "PRIMARY KEY (id_pedido, id_produto), "
                    + "FOREIGN KEY (id_pedido) REFERENCES pedido (id_pedido) ON DELETE CASCADE, "
                    + "FOREIGN KEY (id_produto) REFERENCES produto (id_produto))");
        }
    }
}
