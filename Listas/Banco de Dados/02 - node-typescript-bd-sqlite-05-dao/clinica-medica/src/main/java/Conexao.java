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

    /** Endereço do banco de dados SQLite (arquivo clinica.db). */
    private static final String URL = "jdbc:sqlite:clinica.db";

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
            stmt.execute("CREATE TABLE IF NOT EXISTS paciente ("
                    + "id_paciente INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nome TEXT NOT NULL)");
            stmt.execute("CREATE TABLE IF NOT EXISTS medico ("
                    + "id_medico INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nome TEXT NOT NULL, "
                    + "especialidade TEXT NOT NULL)");
            stmt.execute("CREATE TABLE IF NOT EXISTS consulta ("
                    + "id_consulta INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "id_paciente INTEGER NOT NULL, "
                    + "id_medico INTEGER NOT NULL, "
                    + "data_consulta TEXT NOT NULL, "
                    + "valor REAL NOT NULL, "
                    + "FOREIGN KEY (id_paciente) REFERENCES paciente (id_paciente), "
                    + "FOREIGN KEY (id_medico) REFERENCES medico (id_medico))");
        }
    }
}
