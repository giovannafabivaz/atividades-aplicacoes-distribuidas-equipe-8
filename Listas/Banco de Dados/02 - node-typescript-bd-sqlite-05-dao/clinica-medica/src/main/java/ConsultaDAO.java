import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) responsável por todas as operações de
 * banco de dados da entidade {@link Consulta} (tabela {@code consulta}).
 */
public class ConsultaDAO {

    /**
     * Construtor padrão do DAO.
     */
    public ConsultaDAO() {
    }

    /**
     * Insere um novo registro no banco de dados.
     *
     * @param consulta objeto a ser cadastrado
     * @throws SQLException se ocorrer erro na inserção
     */
    public void inserir(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO consulta (id_paciente, id_medico, data_consulta, valor) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, consulta.getIdPaciente());
            ps.setInt(2, consulta.getIdMedico());
            ps.setString(3, consulta.getDataConsulta());
            ps.setDouble(4, consulta.getValor());
            ps.executeUpdate();
        }
    }

    /**
     * Lista todos os registros cadastrados.
     *
     * @return lista com todos os registros (vazia se não houver nenhum)
     * @throws SQLException se ocorrer erro na consulta
     */
    public List<Consulta> listar() throws SQLException {
        String sql = "SELECT * FROM consulta ORDER BY id_consulta";
        List<Consulta> lista = new ArrayList<>();

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
     * @param idConsulta o identificador da consulta
     * @return o registro encontrado ou {@code null} se não existir
     * @throws SQLException se ocorrer erro na consulta
     */
    public Consulta buscarPorId(int idConsulta) throws SQLException {
        String sql = "SELECT * FROM consulta WHERE id_consulta = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idConsulta);
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
     * @param consulta objeto com os dados atualizados (identificador preenchido)
     * @return {@code true} se algum registro foi atualizado
     * @throws SQLException se ocorrer erro na atualização
     */
    public boolean atualizar(Consulta consulta) throws SQLException {
        String sql = "UPDATE consulta SET id_paciente = ?, id_medico = ?, data_consulta = ?, valor = ? WHERE id_consulta = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, consulta.getIdPaciente());
            ps.setInt(2, consulta.getIdMedico());
            ps.setString(3, consulta.getDataConsulta());
            ps.setDouble(4, consulta.getValor());
            ps.setInt(5, consulta.getIdConsulta());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Exclui um registro do banco de dados.
     *
     * @param idConsulta o identificador da consulta
     * @return {@code true} se algum registro foi excluído
     * @throws SQLException se ocorrer erro na exclusão
     */
    public boolean excluir(int idConsulta) throws SQLException {
        String sql = "DELETE FROM consulta WHERE id_consulta = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idConsulta);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Converte a linha atual de um {@link ResultSet} em um objeto {@link Consulta}.
     *
     * @param rs resultado da consulta posicionado em uma linha
     * @return objeto montado com os dados da linha
     * @throws SQLException se ocorrer erro na leitura das colunas
     */
    private Consulta montar(ResultSet rs) throws SQLException {
        Consulta obj = new Consulta();
        obj.setIdConsulta(rs.getInt("id_consulta"));
        obj.setIdPaciente(rs.getInt("id_paciente"));
        obj.setIdMedico(rs.getInt("id_medico"));
        obj.setDataConsulta(rs.getString("data_consulta"));
        obj.setValor(rs.getDouble("valor"));
        return obj;
    }
}
