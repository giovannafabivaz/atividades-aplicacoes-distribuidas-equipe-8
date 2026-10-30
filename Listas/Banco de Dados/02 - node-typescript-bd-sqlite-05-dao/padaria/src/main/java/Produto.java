/**
 * Representa um produto vendido na padaria.
 * Corresponde à tabela {@code produto} do banco de dados.
 */
public class Produto {

    /** O identificador do produto. */
    private int idProduto;

    /** O nome do produto. */
    private String nome;

    /** O preço do produto. */
    private double preco;

    /**
     * Construtor vazio.
     */
    public Produto() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param idProduto o identificador do produto
     * @param nome o nome do produto
     * @param preco o preço do produto
     */
    public Produto(int idProduto, String nome, double preco) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Retorna o identificador do produto.
     *
     * @return o identificador do produto
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * Define o identificador do produto.
     *
     * @param idProduto novo valor do campo idProduto
     */
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return o nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome novo valor do campo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o preço do produto.
     *
     * @return o preço do produto
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
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
        return "Produto [" + "idProduto=" + idProduto + ", nome=" + nome + ", preco=" + preco + "]";
    }
}
