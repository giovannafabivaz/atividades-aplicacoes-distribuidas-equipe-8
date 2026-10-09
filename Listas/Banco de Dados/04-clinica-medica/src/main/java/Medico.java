/**
 * Representa um médico da clínica.
 * Corresponde à tabela {@code medico} do banco de dados.
 */
public class Medico {

    /** O identificador do médico. */
    private int idMedico;

    /** O nome do médico. */
    private String nome;

    /** A especialidade do médico. */
    private String especialidade;

    /**
     * Construtor vazio.
     */
    public Medico() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param idMedico o identificador do médico
     * @param nome o nome do médico
     * @param especialidade a especialidade do médico
     */
    public Medico(int idMedico, String nome, String especialidade) {
        this.idMedico = idMedico;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    /**
     * Retorna o identificador do médico.
     *
     * @return o identificador do médico
     */
    public int getIdMedico() {
        return idMedico;
    }

    /**
     * Define o identificador do médico.
     *
     * @param idMedico novo valor do campo idMedico
     */
    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    /**
     * Retorna o nome do médico.
     *
     * @return o nome do médico
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do médico.
     *
     * @param nome novo valor do campo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a especialidade do médico.
     *
     * @return a especialidade do médico
     */
    public String getEspecialidade() {
        return especialidade;
    }

    /**
     * Define a especialidade do médico.
     *
     * @param especialidade novo valor do campo especialidade
     */
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    /**
     * Retorna uma representação em texto do objeto.
     *
     * @return texto com os dados do registro
     */
    @Override
    public String toString() {
        return "Medico [" + "idMedico=" + idMedico + ", nome=" + nome + ", especialidade=" + especialidade + "]";
    }
}
