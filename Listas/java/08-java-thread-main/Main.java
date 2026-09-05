// Exemplo de Semáforo:
public class Main {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento();

        // Criamos 8 carros (threads) para disputar 3 vagas no estacionamento:
        for (int i = 1; i <= 8; i++) {
            String nomeCarro = "Carro_" + i;
            new Thread(() -> estacionamento.entrar(nomeCarro)).start();
        }
    }
}
