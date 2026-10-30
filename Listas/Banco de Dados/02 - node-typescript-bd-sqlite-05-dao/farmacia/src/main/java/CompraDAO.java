import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) responsável por todas as operações de
 * banco de dados da entidade {@link Compra} (tabela {@code compra}).
 */
public class CompraDAO {

    /**
     * Construtor padrão do DAO.
     */
    public CompraDAO() {
    }

    /**
     * Insere um novo registro no banco de dados.
     *
     * @param compra objeto a ser cadastrado
     * @throws SQLException se ocorrer erro na inserção
     */
    public void inserir(Compra compra) throws SQLException {
        String sql = "INSERT INTO compra (id_medicamento, id_fornecedor, quantidade, data_compra) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, compra.getIdMedicamento());
            ps.setInt(2, compra.getIdFornecedor());
            ps.setInt(3, compra.getQuantidade());
            ps.setString(4, compra.getDataCompra());
            ps.executeUpdate();
        }
    }

    /**
     * Lista todos os registros cadastrados.
     *
     * @return lista com todos os registros (vazia se não houver nenhum)
     * @throws SQLException se ocorrer erro na consulta
     */
    public List<Compra> listar() throws SQLException {
        String sql = "SELECT * FROM compra ORDER BY id_compra";
        List<Compra> lista = new ArrayList<>();

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
     * @param idCompra o identificador da compra
     * @return o registro encontrado ou {@code null} se não existir
     * @throws SQLException se ocorrer erro na consulta
     */
    public Compra buscarPorId(int idCompra) throws SQLException {
        String sql = "SELECT * FROM compra WHERE id_compra = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
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
     * @param compra objeto com os dados atualizados (identificador preenchido)
     * @return {@code true} se algum registro foi atualizado
     * @throws SQLException se ocorrer erro na atualização
     */
    public boolean atualizar(Compra compra) throws SQLException {
        String sql = "UPDATE compra SET id_medicamento = ?, id_fornecedor = ?, quantidade = ?, data_compra = ? WHERE id_compra = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, compra.getIdMedicamento());
            ps.setInt(2, compra.getIdFornecedor());
            ps.setInt(3, compra.getQuantidade());
            ps.setString(4, compra.getDataCompra());
            ps.setInt(5, compra.getIdCompra());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Exclui um registro do banco de dados.
     *
     * @param idCompra o identificador da compra
     * @return {@code true} se algum registro foi excluído
     * @throws SQLException se ocorrer erro na exclusão
     */
    public boolean excluir(int idCompra) throws SQLException {
        String sql = "DELETE FROM compra WHERE id_compra = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Converte a linha atual de um {@link ResultSet} em um objeto {@link Compra}.
     *
     * @param rs resultado da consulta posicionado em uma linha
     * @return objeto montado com os dados da linha
     * @throws SQLException se ocorrer erro na leitura das colunas
     */
    private Compra montar(ResultSet rs) throws SQLException {
        Compra obj = new Compra();
        obj.setIdCompra(rs.getInt("id_compra"));
        obj.setIdMedicamento(rs.getInt("id_medicamento"));
        obj.setIdFornecedor(rs.getInt("id_fornecedor"));
        obj.setQuantidade(rs.getInt("quantidade"));
        obj.setDataCompra(rs.getString("data_compra"));
        return obj;
    }
}
