import java.util.concurrent.locks.ReentrantLock;

public class GravadorLog {
    private ReentrantLock mutex = new ReentrantLock();

    public void escreverMensagem(String msg, String nomeThread) {
        // O mutex garante que a mensagem inteira seja escrita antes de outra thread interromper
        mutex.lock(); 
        try {
            System.out.println("[" + nomeThread + "] Abrindo arquivo de log...");
            System.out.println("[" + nomeThread + "] Gravando: " + msg);
            
            // Simula a lentidão da gravação
            Thread.sleep(300); 
            
            System.out.println("[" + nomeThread + "] Fechando arquivo de log...\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.unlock();
        }
    }
}