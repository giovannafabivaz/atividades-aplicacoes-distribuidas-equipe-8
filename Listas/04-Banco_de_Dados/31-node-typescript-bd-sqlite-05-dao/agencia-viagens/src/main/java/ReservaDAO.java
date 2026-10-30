import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) responsável por todas as operações de
 * banco de dados da entidade {@link Reserva} (tabela {@code reserva}).
 */
public class ReservaDAO {

    /**
     * Construtor padrão do DAO.
     */
    public ReservaDAO() {
    }

    /**
     * Insere um novo registro no banco de dados.
     *
     * @param reserva objeto a ser cadastrado
     * @throws SQLException se ocorrer erro na inserção
     */
    public void inserir(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO reserva (id_cliente, id_destino, data_viagem, valor) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reserva.getIdCliente());
            ps.setInt(2, reserva.getIdDestino());
            ps.setString(3, reserva.getDataViagem());
            ps.setDouble(4, reserva.getValor());
            ps.executeUpdate();
        }
    }

    /**
     * Lista todos os registros cadastrados.
     *
     * @return lista com todos os registros (vazia se não houver nenhum)
     * @throws SQLException se ocorrer erro na consulta
     */
    public List<Reserva> listar() throws SQLException {
        String sql = "SELECT * FROM reserva ORDER BY id_reserva";
        List<Reserva> lista = new ArrayList<>();

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
     * @param idReserva o identificador da reserva
     * @return o registro encontrado ou {@code null} se não existir
     * @throws SQLException se ocorrer erro na consulta
     */
    public Reserva buscarPorId(int idReserva) throws SQLException {
        String sql = "SELECT * FROM reserva WHERE id_reserva = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
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
     * @param reserva objeto com os dados atualizados (identificador preenchido)
     * @return {@code true} se algum registro foi atualizado
     * @throws SQLException se ocorrer erro na atualização
     */
    public boolean atualizar(Reserva reserva) throws SQLException {
        String sql = "UPDATE reserva SET id_cliente = ?, id_destino = ?, data_viagem = ?, valor = ? WHERE id_reserva = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reserva.getIdCliente());
            ps.setInt(2, reserva.getIdDestino());
            ps.setString(3, reserva.getDataViagem());
            ps.setDouble(4, reserva.getValor());
            ps.setInt(5, reserva.getIdReserva());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Exclui um registro do banco de dados.
     *
     * @param idReserva o identificador da reserva
     * @return {@code true} se algum registro foi excluído
     * @throws SQLException se ocorrer erro na exclusão
     */
    public boolean excluir(int idReserva) throws SQLException {
        String sql = "DELETE FROM reserva WHERE id_reserva = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Converte a linha atual de um {@link ResultSet} em um objeto {@link Reserva}.
     *
     * @param rs resultado da consulta posicionado em uma linha
     * @return objeto montado com os dados da linha
     * @throws SQLException se ocorrer erro na leitura das colunas
     */
    private Reserva montar(ResultSet rs) throws SQLException {
        Reserva obj = new Reserva();
        obj.setIdReserva(rs.getInt("id_reserva"));
        obj.setIdCliente(rs.getInt("id_cliente"));
        obj.setIdDestino(rs.getInt("id_destino"));
        obj.setDataViagem(rs.getString("data_viagem"));
        obj.setValor(rs.getDouble("valor"));
        return obj;
    }
}
