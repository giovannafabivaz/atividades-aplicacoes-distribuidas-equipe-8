import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) responsável por todas as operações de
 * banco de dados da entidade {@link Medico} (tabela {@code medico}).
 */
public class MedicoDAO {

    /**
     * Construtor padrão do DAO.
     */
    public MedicoDAO() {
    }

    /**
     * Insere um novo registro no banco de dados.
     *
     * @param medico objeto a ser cadastrado
     * @throws SQLException se ocorrer erro na inserção
     */
    public void inserir(Medico medico) throws SQLException {
        String sql = "INSERT INTO medico (nome, especialidade) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, medico.getNome());
            ps.setString(2, medico.getEspecialidade());
            ps.executeUpdate();
        }
    }

    /**
     * Lista todos os registros cadastrados.
     *
     * @return lista com todos os registros (vazia se não houver nenhum)
     * @throws SQLException se ocorrer erro na consulta
     */
    public List<Medico> listar() throws SQLException {
        String sql = "SELECT * FROM medico ORDER BY id_medico";
        List<Medico> lista = new ArrayList<>();

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
     * @param idMedico o identificador do médico
     * @return o registro encontrado ou {@code null} se não existir
     * @throws SQLException se ocorrer erro na consulta
     */
    public Medico buscarPorId(int idMedico) throws SQLException {
        String sql = "SELECT * FROM medico WHERE id_medico = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMedico);
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
     * @param medico objeto com os dados atualizados (identificador preenchido)
     * @return {@code true} se algum registro foi atualizado
     * @throws SQLException se ocorrer erro na atualização
     */
    public boolean atualizar(Medico medico) throws SQLException {
        String sql = "UPDATE medico SET nome = ?, especialidade = ? WHERE id_medico = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, medico.getNome());
            ps.setString(2, medico.getEspecialidade());
            ps.setInt(3, medico.getIdMedico());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Exclui um registro do banco de dados.
     *
     * @param idMedico o identificador do médico
     * @return {@code true} se algum registro foi excluído
     * @throws SQLException se ocorrer erro na exclusão
     */
    public boolean excluir(int idMedico) throws SQLException {
        String sql = "DELETE FROM medico WHERE id_medico = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMedico);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Converte a linha atual de um {@link ResultSet} em um objeto {@link Medico}.
     *
     * @param rs resultado da consulta posicionado em uma linha
     * @return objeto montado com os dados da linha
     * @throws SQLException se ocorrer erro na leitura das colunas
     */
    private Medico montar(ResultSet rs) throws SQLException {
        Medico obj = new Medico();
        obj.setIdMedico(rs.getInt("id_medico"));
        obj.setNome(rs.getString("nome"));
        obj.setEspecialidade(rs.getString("especialidade"));
        return obj;
    }
}
