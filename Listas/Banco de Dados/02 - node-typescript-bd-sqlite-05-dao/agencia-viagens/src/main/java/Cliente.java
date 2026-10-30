/**
 * Representa um cliente da agência de viagens.
 * Corresponde à tabela {@code cliente} do banco de dados.
 */
public class Cliente {

    /** O identificador do cliente. */
    private int idCliente;

    /** O nome do cliente. */
    private String nome;

    /**
     * Construtor vazio.
     */
    public Cliente() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param idCliente o identificador do cliente
     * @param nome o nome do cliente
     */
    public Cliente(int idCliente, String nome) {
        this.idCliente = idCliente;
        this.nome = nome;
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
     * Retorna o nome do cliente.
     *
     * @return o nome do cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
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
        return "Cliente [" + "idCliente=" + idCliente + ", nome=" + nome + "]";
    }
}
