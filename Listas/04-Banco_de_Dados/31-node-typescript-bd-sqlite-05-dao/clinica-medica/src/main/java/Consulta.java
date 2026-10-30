/**
 * Representa uma consulta entre um paciente e um médico.
 * Corresponde à tabela {@code consulta} do banco de dados.
 */
public class Consulta {

    /** O identificador da consulta. */
    private int idConsulta;

    /** O identificador do paciente. */
    private int idPaciente;

    /** O identificador do médico. */
    private int idMedico;

    /** A data da consulta. */
    private String dataConsulta;

    /** O valor da consulta. */
    private double valor;

    /**
     * Construtor vazio.
     */
    public Consulta() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param idConsulta o identificador da consulta
     * @param idPaciente o identificador do paciente
     * @param idMedico o identificador do médico
     * @param dataConsulta a data da consulta
     * @param valor o valor da consulta
     */
    public Consulta(int idConsulta, int idPaciente, int idMedico, String dataConsulta, double valor) {
        this.idConsulta = idConsulta;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.dataConsulta = dataConsulta;
        this.valor = valor;
    }

    /**
     * Retorna o identificador da consulta.
     *
     * @return o identificador da consulta
     */
    public int getIdConsulta() {
        return idConsulta;
    }

    /**
     * Define o identificador da consulta.
     *
     * @param idConsulta novo valor do campo idConsulta
     */
    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
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
     * Retorna a data da consulta.
     *
     * @return a data da consulta
     */
    public String getDataConsulta() {
        return dataConsulta;
    }

    /**
     * Define a data da consulta.
     *
     * @param dataConsulta novo valor do campo dataConsulta
     */
    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    /**
     * Retorna o valor da consulta.
     *
     * @return o valor da consulta
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor da consulta.
     *
     * @param valor novo valor do campo valor
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Retorna uma representação em texto do objeto.
     *
     * @return texto com os dados do registro
     */
    @Override
    public String toString() {
        return "Consulta [" + "idConsulta=" + idConsulta + ", idPaciente=" + idPaciente + ", idMedico=" + idMedico + ", dataConsulta=" + dataConsulta + ", valor=" + valor + "]";
    }
}
