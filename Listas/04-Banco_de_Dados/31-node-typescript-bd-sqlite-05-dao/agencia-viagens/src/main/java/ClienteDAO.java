import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) responsável por todas as operações de
 * banco de dados da entidade {@link Cliente} (tabela {@code cliente}).
 */
public class ClienteDAO {

    /**
     * Construtor padrão do DAO.
     */
    public ClienteDAO() {
    }

    /**
     * Insere um novo registro no banco de dados.
     *
     * @param cliente objeto a ser cadastrado
     * @throws SQLException se ocorrer erro na inserção
     */
    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nome) VALUES (?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.executeUpdate();
        }
    }

    /**
     * Lista todos os registros cadastrados.
     *
     * @return lista com todos os registros (vazia se não houver nenhum)
     * @throws SQLException se ocorrer erro na consulta
     */
    public List<Cliente> listar() throws SQLException {
        String sql = "SELECT * FROM cliente ORDER BY id_cliente";
        List<Cliente> lista = new ArrayList<>();

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
     * @param idCliente o identificador do cliente
     * @return o registro encontrado ou {@code null} se não existir
     * @throws SQLException se ocorrer erro na consulta
     */
    public Cliente buscarPorId(int idCliente) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
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
     * @param cliente objeto com os dados atualizados (identificador preenchido)
     * @return {@code true} se algum registro foi atualizado
     * @throws SQLException se ocorrer erro na atualização
     */
    public boolean atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET nome = ? WHERE id_cliente = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setInt(2, cliente.getIdCliente());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Exclui um registro do banco de dados.
     *
     * @param idCliente o identificador do cliente
     * @return {@code true} se algum registro foi excluído
     * @throws SQLException se ocorrer erro na exclusão
     */
    public boolean excluir(int idCliente) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Converte a linha atual de um {@link ResultSet} em um objeto {@link Cliente}.
     *
     * @param rs resultado da consulta posicionado em uma linha
     * @return objeto montado com os dados da linha
     * @throws SQLException se ocorrer erro na leitura das colunas
     */
    private Cliente montar(ResultSet rs) throws SQLException {
        Cliente obj = new Cliente();
        obj.setIdCliente(rs.getInt("id_cliente"));
        obj.setNome(rs.getString("nome"));
        return obj;
    }
}
