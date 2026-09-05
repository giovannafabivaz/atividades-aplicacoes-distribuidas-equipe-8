import java.util.concurrent.Semaphore;

public class ClienteAPI implements Runnable {
    private int idCliente;
    private Semaphore semaforo;

    public ClienteAPI(int idCliente, Semaphore semaforo) {
        this.idCliente = idCliente;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            System.out.println("Cliente " + idCliente + " tentando conectar na API...");
            // O semáforo bloqueia se já houverem 3 processando
            semaforo.acquire(); 
            System.out.println("Cliente " + idCliente + " PROCESSANDO consulta no BD...");
            Thread.sleep(800); 
            System.out.println("Cliente " + idCliente + " finalizou a consulta e liberou a conexão.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }
    }
}