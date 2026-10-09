/**
 * Representa um item (produto e quantidade) de um pedido.
 * Corresponde à tabela {@code item_pedido} do banco de dados.
 */
public class ItemPedido {

    /** O identificador do pedido. */
    private int idPedido;

    /** O identificador do produto. */
    private int idProduto;

    /** A quantidade do produto no pedido. */
    private int quantidade;

    /**
     * Construtor vazio.
     */
    public ItemPedido() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param idPedido o identificador do pedido
     * @param idProduto o identificador do produto
     * @param quantidade a quantidade do produto no pedido
     */
    public ItemPedido(int idPedido, int idProduto, int quantidade) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    /**
     * Retorna o identificador do pedido.
     *
     * @return o identificador do pedido
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * Define o identificador do pedido.
     *
     * @param idPedido novo valor do campo idPedido
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
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
     * Retorna a quantidade do produto no pedido.
     *
     * @return a quantidade do produto no pedido
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade do produto no pedido.
     *
     * @param quantidade novo valor do campo quantidade
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna uma representação em texto do objeto.
     *
     * @return texto com os dados do registro
     */
    @Override
    public String toString() {
        return "ItemPedido [" + "idPedido=" + idPedido + ", idProduto=" + idProduto + ", quantidade=" + quantidade + "]";
    }
}
