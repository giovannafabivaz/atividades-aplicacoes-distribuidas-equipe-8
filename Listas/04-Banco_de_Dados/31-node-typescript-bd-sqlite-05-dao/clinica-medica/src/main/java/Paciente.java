/**
 * Representa um paciente da clínica.
 * Corresponde à tabela {@code paciente} do banco de dados.
 */
public class Paciente {

    /** O identificador do paciente. */
    private int idPaciente;

    /** O nome do paciente. */
    private String nome;

    /**
     * Construtor vazio.
     */
    public Paciente() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param idPaciente o identificador do paciente
     * @param nome o nome do paciente
     */
    public Paciente(int idPaciente, String nome) {
        this.idPaciente = idPaciente;
        this.nome = nome;
    }

    /**
     * Retorna o identificador do paciente.
     *
     * @return o identificador do paciente
     */
    public int getIdPaciente() {
        return idPaciente;
    }

    /**
     * Define o identificador do paciente.
     *
     * @param idPaciente novo valor do campo idPaciente
     */
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * Retorna o nome do paciente.
     *
     * @return o nome do paciente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do paciente.
     *
     * @param nome novo valor do campo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna uma representação em texto do objeto.
     *
     * @return texto com os dados do registro
     */
    @Override
    public String toString() {
        return "Paciente [" + "idPaciente=" + idPaciente + ", nome=" + nome + "]";
    }
}
