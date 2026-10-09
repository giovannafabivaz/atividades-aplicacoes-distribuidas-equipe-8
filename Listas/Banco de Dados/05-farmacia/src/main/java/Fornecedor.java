/**
 * Representa um fornecedor da farmácia.
 * Corresponde à tabela {@code fornecedor} do banco de dados.
 */
public class Fornecedor {

    /** O identificador do fornecedor. */
    private int idFornecedor;

    /** O nome do fornecedor. */
    private String nome;

    /** A cidade do fornecedor. */
    private String cidade;

    /**
     * Construtor vazio.
     */
    public Fornecedor() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param idFornecedor o identificador do fornecedor
     * @param nome o nome do fornecedor
     * @param cidade a cidade do fornecedor
     */
    public Fornecedor(int idFornecedor, String nome, String cidade) {
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.cidade = cidade;
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
     * Retorna o nome do fornecedor.
     *
     * @return o nome do fornecedor
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do fornecedor.
     *
     * @param nome novo valor do campo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a cidade do fornecedor.
     *
     * @return a cidade do fornecedor
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Define a cidade do fornecedor.
     *
     * @param cidade novo valor do campo cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Retorna uma representação em texto do objeto.
     *
     * @return texto com os dados do registro
     */
    @Override
    public String toString() {
        return "Fornecedor [" + "idFornecedor=" + idFornecedor + ", nome=" + nome + ", cidade=" + cidade + "]";
    }
}
