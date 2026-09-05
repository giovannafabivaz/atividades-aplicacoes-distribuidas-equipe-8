import java.util.concurrent.Semaphore;
import java.util.Random;

public class AlunoLaboratorio implements Runnable {
    private String nomeAluno;
    private Semaphore semaforo;
    private Random random = new Random();

    public AlunoLaboratorio(String nomeAluno, Semaphore semaforo) {
        this.nomeAluno = nomeAluno;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            System.out.println(nomeAluno + " chegou no laboratório.");
            // Adquire um dos computadores disponíveis
            semaforo.acquire(); 
            System.out.println(nomeAluno + " PEGOU um computador e começou a estudar.");
            
            // Estuda por um tempo aleatório
            Thread.sleep(random.nextInt(1000) + 500); 
            
            System.out.println(nomeAluno + " terminou e LIBEROU o computador para o próximo da fila.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Libera o computador
            semaforo.release();
        }
    }
}