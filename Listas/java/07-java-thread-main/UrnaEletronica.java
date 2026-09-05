import java.util.concurrent.locks.ReentrantLock;

public class UrnaEletronica implements Runnable {
    // Variável global para contabilizar os votos de todas as instâncias
    private static int totalVotos = 0;
    
    // Mutex global para garantir que só uma thread compute o voto por vez
    private static ReentrantLock mutex = new ReentrantLock();

    @Override
    public void run() {
        // Loop adicionando 100 votos ao contador global
        for (int i = 0; i < 100; i++) {
            mutex.lock(); // Início da Seção Crítica
            try {
                totalVotos++;
            } finally {
                mutex.unlock(); // Fim da Seção Crítica
            }
        }
    }

    public static int getTotalVotos() {
        return totalVotos;
    }
}