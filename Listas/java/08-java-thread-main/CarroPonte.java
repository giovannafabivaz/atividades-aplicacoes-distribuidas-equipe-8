import java.util.concurrent.Semaphore;

public class CarroPonte implements Runnable {
    private String direcao;
    private Semaphore semaforo;

    public CarroPonte(String direcao, Semaphore semaforo) {
        this.direcao = direcao;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            System.out.println("Carro vindo da " + direcao + " quer atravessar a ponte.");
            // Usa o acquire para garantir que a ponte estreita não tenha colisões]
            semaforo.acquire(); 
            System.out.println("Carro vindo da " + direcao + " ESTÁ ATRAVESSANDO.");
            Thread.sleep(600); 
            System.out.println("Carro vindo da " + direcao + " terminou a travessia.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Usa o release para liberar a ponte para o próximo
            semaforo.release(); 
        }
    }
}