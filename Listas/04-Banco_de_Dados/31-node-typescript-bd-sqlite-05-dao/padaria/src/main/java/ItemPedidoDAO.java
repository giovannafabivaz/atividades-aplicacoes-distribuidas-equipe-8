import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) responsável por todas as operações de
 * banco de dados da entidade {@link ItemPedido} (tabela {@code item_pedido}).
 */
public class ItemPedidoDAO {

    /**
     * Construtor padrão do DAO.
     */
    public ItemPedidoDAO() {
    }

    /**
     * Insere um novo registro no banco de dados.
     *
     * @param itemPedido objeto a ser cadastrado
     * @throws SQLException se ocorrer erro na inserção
     */
    public void inserir(ItemPedido itemPedido) throws SQLException {
        String sql = "INSERT INTO item_pedido (id_pedido, id_produto, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, itemPedido.getIdPedido());
            ps.setInt(2, itemPedido.getIdProduto());
            ps.setInt(3, itemPedido.getQuantidade());
            ps.executeUpdate();
        }
    }

    /**
     * Lista todos os registros cadastrados.
     *
     * @return lista com todos os registros (vazia se não houver nenhum)
     * @throws SQLException se ocorrer erro na consulta
     */
    public List<ItemPedido> listar() throws SQLException {
        String sql = "SELECT * FROM item_pedido ORDER BY id_pedido, id_produto";
        List<ItemPedido> lista = new ArrayList<>();

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
     * @param idProduto o identificador do produto
     * @return o registro encontrado ou {@code null} se não existir
     * @throws SQLException se ocorrer erro na consulta
     */
    public ItemPedido buscarPorId(int idPedido, int idProduto) throws SQLException {
        String sql = "SELECT * FROM item_pedido WHERE id_pedido = ? AND id_produto = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ps.setInt(2, idProduto);
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
     * @param itemPedido objeto com os dados atualizados (identificador preenchido)
     * @return {@code true} se algum registro foi atualizado
     * @throws SQLException se ocorrer erro na atualização
     */
    public boolean atualizar(ItemPedido itemPedido) throws SQLException {
        String sql = "UPDATE item_pedido SET quantidade = ? WHERE id_pedido = ? AND id_produto = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, itemPedido.getQuantidade());
            ps.setInt(2, itemPedido.getIdPedido());
            ps.setInt(3, itemPedido.getIdProduto());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Exclui um registro do banco de dados.
     *
     * @param idPedido o identificador do pedido
     * @param idProduto o identificador do produto
     * @return {@code true} se algum registro foi excluído
     * @throws SQLException se ocorrer erro na exclusão
     */
    public boolean excluir(int idPedido, int idProduto) throws SQLException {
        String sql = "DELETE FROM item_pedido WHERE id_pedido = ? AND id_produto = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ps.setInt(2, idProduto);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Converte a linha atual de um {@link ResultSet} em um objeto {@link ItemPedido}.
     *
     * @param rs resultado da consulta posicionado em uma linha
     * @return objeto montado com os dados da linha
     * @throws SQLException se ocorrer erro na leitura das colunas
     */
    private ItemPedido montar(ResultSet rs) throws SQLException {
        ItemPedido obj = new ItemPedido();
        obj.setIdPedido(rs.getInt("id_pedido"));
        obj.setIdProduto(rs.getInt("id_produto"));
        obj.setQuantidade(rs.getInt("quantidade"));
        return obj;
    }
}
