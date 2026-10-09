import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO responsável pelas operações de banco.
 */
public class VeiculoDAO {

    /**
     * Cria a tabela caso não exista.
     */
    public void criarTabela() {

        String sql = """
                CREATE TABLE IF NOT EXISTS veiculos(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                marca TEXT,
                modelo TEXT,
                chassi TEXT,
                placa TEXT,
                cor TEXT
                )
                """;

        try (Connection con = ConexaoSQLite.conectar();
             Statement stmt = con.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Insere um veículo.
     * @param v veículo a ser cadastrado.
     */
    public void inserir(Veiculo v) {

        String sql =
                "INSERT INTO veiculos (marca,modelo,chassi,placa,cor) VALUES (?,?,?,?,?)";

        try (Connection con = ConexaoSQLite.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, v.getMarca());
            pst.setString(2, v.getModelo());
            pst.setString(3, v.getNumeroChassi());
            pst.setString(4, v.getPlaca());
            pst.setString(5, v.getCor());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Retorna todos os veículos cadastrados.
     * @return lista de veículos.
     */
    public List<Veiculo> listar() {

        List<Veiculo> lista = new ArrayList<>();

        String sql = "SELECT * FROM veiculos";

        try (Connection con = ConexaoSQLite.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Veiculo v = new Veiculo();

                v.setId(rs.getInt("id"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setNumeroChassi(rs.getString("chassi"));
                v.setPlaca(rs.getString("placa"));
                v.setCor(rs.getString("cor"));

                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Atualiza um veículo cadastrado.
     * @param v veículo atualizado.
     */
    public void atualizar(Veiculo v) {

        String sql =
                "UPDATE veiculos SET marca=?,modelo=?,chassi=?,placa=?,cor=? WHERE id=?";

        try (Connection con = ConexaoSQLite.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, v.getMarca());
            pst.setString(2, v.getModelo());
            pst.setString(3, v.getNumeroChassi());
            pst.setString(4, v.getPlaca());
            pst.setString(5, v.getCor());
            pst.setInt(6, v.getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Exclui um veículo.
     * @param id identificação do veículo.
     */
    public void excluir(int id) {

        String sql = "DELETE FROM veiculos WHERE id=?";

        try (Connection con = ConexaoSQLite.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}