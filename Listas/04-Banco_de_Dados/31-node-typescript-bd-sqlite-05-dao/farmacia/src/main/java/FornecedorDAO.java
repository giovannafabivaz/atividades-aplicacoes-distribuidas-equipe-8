import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) responsável por todas as operações de
 * banco de dados da entidade {@link Fornecedor} (tabela {@code fornecedor}).
 */
public class FornecedorDAO {

    /**
     * Construtor padrão do DAO.
     */
    public FornecedorDAO() {
    }

    /**
     * Insere um novo registro no banco de dados.
     *
     * @param fornecedor objeto a ser cadastrado
     * @throws SQLException se ocorrer erro na inserção
     */
    public void inserir(Fornecedor fornecedor) throws SQLException {
        String sql = "INSERT INTO fornecedor (nome, cidade) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCidade());
            ps.executeUpdate();
        }
    }

    /**
     * Lista todos os registros cadastrados.
     *
     * @return lista com todos os registros (vazia se não houver nenhum)
     * @throws SQLException se ocorrer erro na consulta
     */
    public List<Fornecedor> listar() throws SQLException {
        String sql = "SELECT * FROM fornecedor ORDER BY id_fornecedor";
        List<Fornecedor> lista = new ArrayList<>();

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
     * @param idFornecedor o identificador do fornecedor
     * @return o registro encontrado ou {@code null} se não existir
     * @throws SQLException se ocorrer erro na consulta
     */
    public Fornecedor buscarPorId(int idFornecedor) throws SQLException {
        String sql = "SELECT * FROM fornecedor WHERE id_fornecedor = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idFornecedor);
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
     * @param fornecedor objeto com os dados atualizados (identificador preenchido)
     * @return {@code true} se algum registro foi atualizado
     * @throws SQLException se ocorrer erro na atualização
     */
    public boolean atualizar(Fornecedor fornecedor) throws SQLException {
        String sql = "UPDATE fornecedor SET nome = ?, cidade = ? WHERE id_fornecedor = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCidade());
            ps.setInt(3, fornecedor.getIdFornecedor());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Exclui um registro do banco de dados.
     *
     * @param idFornecedor o identificador do fornecedor
     * @return {@code true} se algum registro foi excluído
     * @throws SQLException se ocorrer erro na exclusão
     */
    public boolean excluir(int idFornecedor) throws SQLException {
        String sql = "DELETE FROM fornecedor WHERE id_fornecedor = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idFornecedor);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Converte a linha atual de um {@link ResultSet} em um objeto {@link Fornecedor}.
     *
     * @param rs resultado da consulta posicionado em uma linha
     * @return objeto montado com os dados da linha
     * @throws SQLException se ocorrer erro na leitura das colunas
     */
    private Fornecedor montar(ResultSet rs) throws SQLException {
        Fornecedor obj = new Fornecedor();
        obj.setIdFornecedor(rs.getInt("id_fornecedor"));
        obj.setNome(rs.getString("nome"));
        obj.setCidade(rs.getString("cidade"));
        return obj;
    }
}
