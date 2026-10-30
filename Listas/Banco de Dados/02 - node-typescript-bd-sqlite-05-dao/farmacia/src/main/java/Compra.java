/**
 * Representa uma compra de medicamento feita a um fornecedor.
 * Corresponde à tabela {@code compra} do banco de dados.
 */
public class Compra {

    /** O identificador da compra. */
    private int idCompra;

    /** O identificador do medicamento. */
    private int idMedicamento;

    /** O identificador do fornecedor. */
    private int idFornecedor;

    /** A quantidade comprada. */
    private int quantidade;

    /** A data da compra. */
    private String dataCompra;

    /**
     * Construtor vazio.
     */
    public Compra() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param idCompra o identificador da compra
     * @param idMedicamento o identificador do medicamento
     * @param idFornecedor o identificador do fornecedor
     * @param quantidade a quantidade comprada
     * @param dataCompra a data da compra
     */
    public Compra(int idCompra, int idMedicamento, int idFornecedor, int quantidade, String dataCompra) {
        this.idCompra = idCompra;
        this.idMedicamento = idMedicamento;
        this.idFornecedor = idFornecedor;
        this.quantidade = quantidade;
        this.dataCompra = dataCompra;
    }

    /**
     * Retorna o identificador da compra.
     *
     * @return o identificador da compra
     */
    public int getIdCompra() {
        return idCompra;
    }

    /**
     * Define o identificador da compra.
     *
     * @param idCompra novo valor do campo idCompra
     */
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
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
     * Retorna o identificador do fornecedor.
     *
     * @return o identificador do fornecedor
     */
    public int getIdFornecedor() {
        return idFornecedor;
    }

    /**
     * Define o identificador do fornecedor.
     *
     * @param idFornecedor novo valor do campo idFornecedor
     */
    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    /**
     * Retorna a quantidade comprada.
     *
     * @return a quantidade comprada
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade comprada.
     *
     * @param quantidade novo valor do campo quantidade
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna a data da compra.
     *
     * @return a data da compra
     */
    public String getDataCompra() {
        return dataCompra;
    }

    /**
     * Define a data da compra.
     *
     * @param dataCompra novo valor do campo dataCompra
     */
    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    /**
     * Retorna uma representação em texto do objeto.
     *
     * @return texto com os dados do registro
     */
    @Override
    public String toString() {
        return "Compra [" + "idCompra=" + idCompra + ", idMedicamento=" + idMedicamento + ", idFornecedor=" + idFornecedor + ", quantidade=" + quantidade + ", dataCompra=" + dataCompra + "]";
    }
}
