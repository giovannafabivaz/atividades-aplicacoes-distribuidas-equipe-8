import java.util.concurrent.Semaphore;

public class ClienteBuffet implements Runnable {
    private String nomeCliente;
    private Semaphore semaforo;

    public ClienteBuffet(String nomeCliente, Semaphore semaforo) {
        this.nomeCliente = nomeCliente;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            System.out.println(nomeCliente + " está na fila do buffet.");
            // Gerencia a entrada na pista de comida limitando a capacidade física
            semaforo.acquire(); 
            System.out.println(nomeCliente + " está se SERVINDO.");
            Thread.sleep(700); 
            System.out.println(nomeCliente + " terminou de montar o prato e abriu espaço na pista.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }
    }
}