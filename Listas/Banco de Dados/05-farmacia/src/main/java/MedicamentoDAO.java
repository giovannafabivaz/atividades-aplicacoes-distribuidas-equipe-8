import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) responsável por todas as operações de
 * banco de dados da entidade {@link Medicamento} (tabela {@code medicamento}).
 */
public class MedicamentoDAO {

    /**
     * Construtor padrão do DAO.
     */
    public MedicamentoDAO() {
    }

    /**
     * Insere um novo registro no banco de dados.
     *
     * @param medicamento objeto a ser cadastrado
     * @throws SQLException se ocorrer erro na inserção
     */
    public void inserir(Medicamento medicamento) throws SQLException {
        String sql = "INSERT INTO medicamento (nome, preco) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, medicamento.getNome());
            ps.setDouble(2, medicamento.getPreco());
            ps.executeUpdate();
        }
    }

    /**
     * Lista todos os registros cadastrados.
     *
     * @return lista com todos os registros (vazia se não houver nenhum)
     * @throws SQLException se ocorrer erro na consulta
     */
    public List<Medicamento> listar() throws SQLException {
        String sql = "SELECT * FROM medicamento ORDER BY id_medicamento";
        List<Medicamento> lista = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    /**
     * Busca um registro pelo seu identificador.
     *
     * @param idMedicamento o identificador do medicamento
     * @return o registro encontrado ou {@code null} se não existir
     * @throws SQLException se ocorrer erro na consulta
     */
    public Medicamento buscarPorId(int idMedicamento) throws SQLException {
        String sql = "SELECT * FROM medicamento WHERE id_medicamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMedicamento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return montar(rs);
                }
            }
        }
        return null;
    }

    /**
     * Atualiza os dados de um registro já cadastrado.
     * O registro é localizado pelo seu identificador.
     *
     * @param medicamento objeto com os dados atualizados (identificador preenchido)
     * @return {@code true} se algum registro foi atualizado
     * @throws SQLException se ocorrer erro na atualização
     */
    public boolean atualizar(Medicamento medicamento) throws SQLException {
        String sql = "UPDATE medicamento SET nome = ?, preco = ? WHERE id_medicamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, medicamento.getNome());
            ps.setDouble(2, medicamento.getPreco());
            ps.setInt(3, medicamento.getIdMedicamento());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Exclui um registro do banco de dados.
     *
     * @param idMedicamento o identificador do medicamento
     * @return {@code true} se algum registro foi excluído
     * @throws SQLException se ocorrer erro na exclusão
     */
    public boolean excluir(int idMedicamento) throws SQLException {
        String sql = "DELETE FROM medicamento WHERE id_medicamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMedicamento);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Converte a linha atual de um {@link ResultSet} em um objeto {@link Medicamento}.
     *
     * @param rs resultado da consulta posicionado em uma linha
     * @return objeto montado com os dados da linha
     * @throws SQLException se ocorrer erro na leitura das colunas
     */
    private Medicamento montar(ResultSet rs) throws SQLException {
        Medicamento obj = new Medicamento();
        obj.setIdMedicamento(rs.getInt("id_medicamento"));
        obj.setNome(rs.getString("nome"));
        obj.setPreco(rs.getDouble("preco"));
        return obj;
    }
}
