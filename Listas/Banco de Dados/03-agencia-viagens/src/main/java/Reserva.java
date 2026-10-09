/**
 * Representa uma reserva de viagem feita por um cliente.
 * Corresponde à tabela {@code reserva} do banco de dados.
 */
public class Reserva {

    /** O identificador da reserva. */
    private int idReserva;

    /** O identificador do cliente. */
    private int idCliente;

    /** O identificador do destino. */
    private int idDestino;

    /** A data da viagem. */
    private String dataViagem;

    /** O valor da reserva. */
    private double valor;

    /**
     * Construtor vazio.
     */
    public Reserva() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param idReserva o identificador da reserva
     * @param idCliente o identificador do cliente
     * @param idDestino o identificador do destino
     * @param dataViagem a data da viagem
     * @param valor o valor da reserva
     */
    public Reserva(int idReserva, int idCliente, int idDestino, String dataViagem, double valor) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.idDestino = idDestino;
        this.dataViagem = dataViagem;
        this.valor = valor;
    }

    /**
     * Retorna o identificador da reserva.
     *
     * @return o identificador da reserva
     */
    public int getIdReserva() {
        return idReserva;
    }

    /**
     * Define o identificador da reserva.
     *
     * @param idReserva novo valor do campo idReserva
     */
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * Retorna o identificador do cliente.
     *
     * @return o identificador do cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Define o identificador do cliente.
     *
     * @param idCliente novo valor do campo idCliente
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Retorna o identificador do destino.
     *
     * @return o identificador do destino
     */
    public int getIdDestino() {
        return idDestino;
    }

    /**
     * Define o identificador do destino.
     *
     * @param idDestino novo valor do campo idDestino
     */
    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    /**
     * Retorna a data da viagem.
     *
     * @return a data da viagem
     */
    public String getDataViagem() {
        return dataViagem;
    }

    /**
     * Define a data da viagem.
     *
     * @param dataViagem novo valor do campo dataViagem
     */
    public void setDataViagem(String dataViagem) {
        this.dataViagem = dataViagem;
    }

    /**
     * Retorna o valor da reserva.
     *
     * @return o valor da reserva
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor da reserva.
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
        return "Reserva [" + "idReserva=" + idReserva + ", idCliente=" + idCliente + ", idDestino=" + idDestino + ", dataViagem=" + dataViagem + ", valor=" + valor + "]";
    }
}
