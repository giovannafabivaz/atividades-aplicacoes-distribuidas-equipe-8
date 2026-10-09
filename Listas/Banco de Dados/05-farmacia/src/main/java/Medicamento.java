/**
 * Representa um medicamento vendido na farmácia.
 * Corresponde à tabela {@code medicamento} do banco de dados.
 */
public class Medicamento {

    /** O identificador do medicamento. */
    private int idMedicamento;

    /** O nome do medicamento. */
    private String nome;

    /** O preço do medicamento. */
    private double preco;

    /**
     * Construtor vazio.
     */
    public Medicamento() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param idMedicamento o identificador do medicamento
     * @param nome o nome do medicamento
     * @param preco o preço do medicamento
     */
    public Medicamento(int idMedicamento, String nome, double preco) {
        this.idMedicamento = idMedicamento;
        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Retorna o identificador do medicamento.
     *
     * @return o identificador do medicamento
     */
    public int getIdMedicamento() {
        return idMedicamento;
    }

    /**
     * Define o identificador do medicamento.
     *
     * @param idMedicamento novo valor do campo idMedicamento
     */
    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    /**
     * Retorna o nome do medicamento.
     *
     * @return o nome do medicamento
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do medicamento.
     *
     * @param nome novo valor do campo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o preço do medicamento.
     *
     * @return o preço do medicamento
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do medicamento.
     *
     * @param preco novo valor do campo preco
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Retorna uma representação em texto do objeto.
     *
     * @return texto com os dados do registro
     */
    @Override
    public String toString() {
        return "Medicamento [" + "idMedicamento=" + idMedicamento + ", nome=" + nome + ", preco=" + preco + "]";
    }
}
