import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) responsável por todas as operações de
 * banco de dados da entidade {@link Pedido} (tabela {@code pedido}).
 */
public class PedidoDAO {

    /**
     * Construtor padrão do DAO.
     */
    public PedidoDAO() {
    }

    /**
     * Insere um novo registro no banco de dados.
     *
     * @param pedido objeto a ser cadastrado
     * @throws SQLException se ocorrer erro na inserção
     */
    public void inserir(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedido (id_cliente, data_pedido, total) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pedido.getIdCliente());
            ps.setString(2, pedido.getDataPedido());
            ps.setDouble(3, pedido.getTotal());
            ps.executeUpdate();
        }
    }

    /**
     * Lista todos os registros cadastrados.
     *
     * @return lista com todos os registros (vazia se não houver nenhum)
     * @throws SQLException se ocorrer erro na consulta
     */
    public List<Pedido> listar() throws SQLException {
        String sql = "SELECT * FROM pedido ORDER BY id_pedido";
        List<Pedido> lista = new ArrayList<>();

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
     * @param idPedido o identificador do pedido
     * @return o registro encontrado ou {@code null} se não existir
     * @throws SQLException se ocorrer erro na consulta
     */
    public Pedido buscarPorId(int idPedido) throws SQLException {
        String sql = "SELECT * FROM pedido WHERE id_pedido = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
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
     * @param pedido objeto com os dados atualizados (identificador preenchido)
     * @return {@code true} se algum registro foi atualizado
     * @throws SQLException se ocorrer erro na atualização
     */
    public boolean atualizar(Pedido pedido) throws SQLException {
        String sql = "UPDATE pedido SET id_cliente = ?, data_pedido = ?, total = ? WHERE id_pedido = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pedido.getIdCliente());
            ps.setString(2, pedido.getDataPedido());
            ps.setDouble(3, pedido.getTotal());
            ps.setInt(4, pedido.getIdPedido());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Exclui um registro do banco de dados.
     *
     * @param idPedido o identificador do pedido
     * @return {@code true} se algum registro foi excluído
     * @throws SQLException se ocorrer erro na exclusão
     */
    public boolean excluir(int idPedido) throws SQLException {
        String sql = "DELETE FROM pedido WHERE id_pedido = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Converte a linha atual de um {@link ResultSet} em um objeto {@link Pedido}.
     *
     * @param rs resultado da consulta posicionado em uma linha
     * @return objeto montado com os dados da linha
     * @throws SQLException se ocorrer erro na leitura das colunas
     */
    private Pedido montar(ResultSet rs) throws SQLException {
        Pedido obj = new Pedido();
        obj.setIdPedido(rs.getInt("id_pedido"));
        obj.setIdCliente(rs.getInt("id_cliente"));
        obj.setDataPedido(rs.getString("data_pedido"));
        obj.setTotal(rs.getDouble("total"));
        return obj;
    }
}
