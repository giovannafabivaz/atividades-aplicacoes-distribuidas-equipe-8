/**
 * Representa um destino de viagem.
 * Corresponde à tabela {@code destino} do banco de dados.
 */
public class Destino {

    /** O identificador do destino. */
    private int idDestino;

    /** O nome do destino. */
    private String nomeDestino;

    /** O país do destino. */
    private String pais;

    /**
     * Construtor vazio.
     */
    public Destino() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param idDestino o identificador do destino
     * @param nomeDestino o nome do destino
     * @param pais o país do destino
     */
    public Destino(int idDestino, String nomeDestino, String pais) {
        this.idDestino = idDestino;
        this.nomeDestino = nomeDestino;
        this.pais = pais;
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
     * Retorna o nome do destino.
     *
     * @return o nome do destino
     */
    public String getNomeDestino() {
        return nomeDestino;
    }

    /**
     * Define o nome do destino.
     *
     * @param nomeDestino novo valor do campo nomeDestino
     */
    public void setNomeDestino(String nomeDestino) {
        this.nomeDestino = nomeDestino;
    }

    /**
     * Retorna o país do destino.
     *
     * @return o país do destino
     */
    public String getPais() {
        return pais;
    }

    /**
     * Define o país do destino.
     *
     * @param pais novo valor do campo pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Retorna uma representação em texto do objeto.
     *
     * @return texto com os dados do registro
     */
    @Override
    public String toString() {
        return "Destino [" + "idDestino=" + idDestino + ", nomeDestino=" + nomeDestino + ", pais=" + pais + "]";
    }
}
