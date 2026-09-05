import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Ex1_TentativaImediata {
    private static int contador = 0;
    // O tryLock() tenta pegar o cadeado
    private static ReentrantLock mutex = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String continuar;
        do {
            System.out.print("\n[Ex1] Informe a quantidade para incrementar: ");
            int qtd = sc.nextInt();
            contador = 0;
            
            Runnable tarefa = () -> {
                String nome = Thread.currentThread().getName();
                // Se estiver livre, ele tranca na hora e retorna true
                if (mutex.tryLock()) { 
                    try {
                        contador += qtd;
                        System.out.println(nome + " conseguiu o lock. Contador: " + contador);
                    } finally {
                        mutex.unlock();
                    }
                } else {
                    // Se estiver ocupado, ele não espera: retorna false imediatamente
                    System.out.println(nome + " desistiu imediatamente (lock ocupado).");
                }
            };

            Thread[] threads = new Thread[4];
            for (int i = 0; i < 4; i++) {
                threads[i] = new Thread(tarefa, "Thread-" + (i + 1));
                threads[i].start();
            }
            for (Thread t : threads) t.join();

            System.out.print("Deseja executar novamente? (s/n): ");
            continuar = sc.next();
        } while (continuar.equalsIgnoreCase("s"));
    }
}