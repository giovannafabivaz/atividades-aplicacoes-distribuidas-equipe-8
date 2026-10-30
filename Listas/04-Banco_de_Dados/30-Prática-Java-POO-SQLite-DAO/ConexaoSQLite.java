import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável pela conexão com o banco SQLite.
 */
public class ConexaoSQLite {

    private static final String URL = "jdbc:sqlite:veiculos.db";

    /**
     * Retorna uma conexão com o banco.
     * @return conexão SQLite.
     * @throws SQLException erro de conexão.
     */
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}