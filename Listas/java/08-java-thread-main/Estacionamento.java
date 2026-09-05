import java.util.concurrent.Semaphore;

public class Estacionamento implements Runnable {
    private String nomeCarro;
    private Semaphore semaforo;

    public Estacionamento(String nomeCarro, Semaphore semaforo) {
        this.nomeCarro = nomeCarro;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            System.out.println(nomeCarro + " está aguardando vaga...");
            semaforo.acquire();
            System.out.println(nomeCarro + " ESTACIONOU.");
            Thread.sleep(1000); 
            System.out.println(nomeCarro + " saiu da vaga.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }
    }
}