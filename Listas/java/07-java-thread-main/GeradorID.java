import java.util.concurrent.locks.ReentrantLock;

public class GeradorID {
    private int idAtual = 0;
    private ReentrantLock mutex = new ReentrantLock();

    public int obterProximoId() {
        mutex.lock(); // Protege o incremento do ID com Mutex
        try {
            idAtual++;
            return idAtual;
        } finally {
            mutex.unlock();
        }
    }
}