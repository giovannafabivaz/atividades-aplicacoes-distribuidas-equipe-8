/**
 * Representa um pedido feito por um cliente da padaria.
 * Corresponde à tabela {@code pedido} do banco de dados.
 */
public class Pedido {

    /** O identificador do pedido. */
    private int idPedido;

    /** O identificador do cliente que fez o pedido. */
    private int idCliente;

    /** A data do pedido. */
    private String dataPedido;

    /** O valor total do pedido. */
    private double total;

    /**
     * Construtor vazio.
     */
    public Pedido() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param idPedido o identificador do pedido
     * @param idCliente o identificador do cliente que fez o pedido
     * @param dataPedido a data do pedido
     * @param total o valor total do pedido
     */
    public Pedido(int idPedido, int idCliente, String dataPedido, double total) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.dataPedido = dataPedido;
        this.total = total;
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
     * Retorna o identificador do cliente que fez o pedido.
     *
     * @return o identificador do cliente que fez o pedido
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Define o identificador do cliente que fez o pedido.
     *
     * @param idCliente novo valor do campo idCliente
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Retorna a data do pedido.
     *
     * @return a data do pedido
     */
    public String getDataPedido() {
        return dataPedido;
    }

    /**
     * Define a data do pedido.
     *
     * @param dataPedido novo valor do campo dataPedido
     */
    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    /**
     * Retorna o valor total do pedido.
     *
     * @return o valor total do pedido
     */
    public double getTotal() {
        return total;
    }

    /**
     * Define o valor total do pedido.
     *
     * @param total novo valor do campo total
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Retorna uma representação em texto do objeto.
     *
     * @return texto com os dados do registro
     */
    @Override
    public String toString() {
        return "Pedido [" + "idPedido=" + idPedido + ", idCliente=" + idCliente + ", dataPedido=" + dataPedido + ", total=" + total + "]";
    }
}
