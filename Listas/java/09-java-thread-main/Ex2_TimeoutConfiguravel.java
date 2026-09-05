import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Ex2_TimeoutConfiguravel {
    private static ReentrantLock mutex = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String continuar;
        Random random = new Random();
        do {
            System.out.print("\n[Ex2] Informe o tempo máximo de espera (segundos): ");
            int timeout = sc.nextInt();
            
            Runnable tarefa = () -> {
                String nome = Thread.currentThread().getName();
                try {
                    // Existe também uma variação que espera por um tempo limite (timeout) antes de desistir
                    if (mutex.tryLock(timeout, TimeUnit.SECONDS)) {
                        try {
                            System.out.println(nome + " conseguiu o lock.");
                            int sleepTime = random.nextInt(4) + 1; // 1 a 4 segundos
                            Thread.sleep(sleepTime * 1000L);
                        } finally {
                            mutex.unlock();
                        }
                    } else {
                        System.out.println(nome + " desistiu por timeout.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            Thread[] threads = new Thread[5];
            for (int i = 0; i < 5; i++) {
                threads[i] = new Thread(tarefa, "Thread-" + (i + 1));
                threads[i].start();
            }
            for (Thread t : threads) t.join();

            System.out.print("Deseja executar novamente? (s/n): ");
            continuar = sc.next();
        } while (continuar.equalsIgnoreCase("s"));
    }
}