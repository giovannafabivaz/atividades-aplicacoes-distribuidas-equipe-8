/**
 * Classe que representa um veículo.
 */
public class Veiculo {

    private int id;
    private String marca;
    private String modelo;
    private String numeroChassi;
    private String placa;
    private String cor;

    /**
     * Construtor vazio.
     */
    public Veiculo() {
    }

    /**
     * Obtém o ID do veículo.
     * @return id do veículo.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do veículo.
     * @param id identificação do veículo.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém a marca.
     * @return marca.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define a marca.
     * @param marca marca do veículo.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtém o modelo.
     * @return modelo.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo.
     * @param modelo modelo do veículo.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtém o número do chassi.
     * @return número do chassi.
     */
    public String getNumeroChassi() {
        return numeroChassi;
    }

    /**
     * Define o número do chassi.
     * @param numeroChassi número do chassi.
     */
    public void setNumeroChassi(String numeroChassi) {
        this.numeroChassi = numeroChassi;
    }

    /**
     * Obtém a placa.
     * @return placa.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Define a placa.
     * @param placa placa do veículo.
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Obtém a cor.
     * @return cor.
     */
    public String getCor() {
        return cor;
    }

    /**
     * Define a cor.
     * @param cor cor do veículo.
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Marca: " + marca +
                " | Modelo: " + modelo +
                " | Chassi: " + numeroChassi +
                " | Placa: " + placa +
                " | Cor: " + cor;
    }
}