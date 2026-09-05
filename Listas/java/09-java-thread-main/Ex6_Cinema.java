import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Ex6_Cinema {
    private boolean[] assentosOcupados = new boolean[10];
    private ReentrantLock mutex = new ReentrantLock();

    public void reservarAssento(int numeroAssento) {
        String nome = Thread.currentThread().getName();
        try {
            // Utilize o método tryLock(3, TimeUnit.SECONDS) para que a thread espere no máximo 3 segundos
            if (mutex.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    if (!assentosOcupados[numeroAssento]) {
                        assentosOcupados[numeroAssento] = true;
                        System.out.println(nome + " comprou o assento " + numeroAssento);
                        // Simular um processamento de 4 segundos (para estourar o tempo das outras)
                        Thread.sleep(4000); 
                    } else {
                        System.out.println(nome + " falhou: assento já comprado.");
                    }
                } finally {
                    mutex.unlock();
                }
            } else {
                System.out.println("Desistência por timeout: Assento muito disputado no momento! (" + nome + ")");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Ex6_Cinema cinema = new Ex6_Cinema();
        Runnable acao = () -> cinema.reservarAssento(5);
        
        new Thread(acao, "Cliente-1").start();
        new Thread(acao, "Cliente-2").start();
        new Thread(acao, "Cliente-3").start();
    }
}