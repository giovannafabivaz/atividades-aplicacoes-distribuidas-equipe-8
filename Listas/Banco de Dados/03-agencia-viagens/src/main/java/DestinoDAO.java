import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) responsável por todas as operações de
 * banco de dados da entidade {@link Destino} (tabela {@code destino}).
 */
public class DestinoDAO {

    /**
     * Construtor padrão do DAO.
     */
    public DestinoDAO() {
    }

    /**
     * Insere um novo registro no banco de dados.
     *
     * @param destino objeto a ser cadastrado
     * @throws SQLException se ocorrer erro na inserção
     */
    public void inserir(Destino destino) throws SQLException {
        String sql = "INSERT INTO destino (nome_destino, pais) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, destino.getNomeDestino());
            ps.setString(2, destino.getPais());
            ps.executeUpdate();
        }
    }

    /**
     * Lista todos os registros cadastrados.
     *
     * @return lista com todos os registros (vazia se não houver nenhum)
     * @throws SQLException se ocorrer erro na consulta
     */
    public List<Destino> listar() throws SQLException {
        String sql = "SELECT * FROM destino ORDER BY id_destino";
        List<Destino> lista = new ArrayList<>();

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
     * @param idDestino o identificador do destino
     * @return o registro encontrado ou {@code null} se não existir
     * @throws SQLException se ocorrer erro na consulta
     */
    public Destino buscarPorId(int idDestino) throws SQLException {
        String sql = "SELECT * FROM destino WHERE id_destino = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idDestino);
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
     * @param destino objeto com os dados atualizados (identificador preenchido)
     * @return {@code true} se algum registro foi atualizado
     * @throws SQLException se ocorrer erro na atualização
     */
    public boolean atualizar(Destino destino) throws SQLException {
        String sql = "UPDATE destino SET nome_destino = ?, pais = ? WHERE id_destino = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, destino.getNomeDestino());
            ps.setString(2, destino.getPais());
            ps.setInt(3, destino.getIdDestino());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Exclui um registro do banco de dados.
     *
     * @param idDestino o identificador do destino
     * @return {@code true} se algum registro foi excluído
     * @throws SQLException se ocorrer erro na exclusão
     */
    public boolean excluir(int idDestino) throws SQLException {
        String sql = "DELETE FROM destino WHERE id_destino = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idDestino);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Converte a linha atual de um {@link ResultSet} em um objeto {@link Destino}.
     *
     * @param rs resultado da consulta posicionado em uma linha
     * @return objeto montado com os dados da linha
     * @throws SQLException se ocorrer erro na leitura das colunas
     */
    private Destino montar(ResultSet rs) throws SQLException {
        Destino obj = new Destino();
        obj.setIdDestino(rs.getInt("id_destino"));
        obj.setNomeDestino(rs.getString("nome_destino"));
        obj.setPais(rs.getString("pais"));
        return obj;
    }
}
