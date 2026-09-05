import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Ex3_LiberacaoGarantida {
    private static ReentrantLock mutex = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String continuar;
        do {
            System.out.print("\n[Ex3] Informe o tempo máximo de espera (segundos): ");
            int timeout = sc.nextInt();
            System.out.print("Informe o primeiro número inteiro: ");
            int num1 = sc.nextInt();
            System.out.print("Informe o segundo número inteiro (use 0 para forçar erro): ");
            int num2 = sc.nextInt();
            
            Runnable tarefa = () -> {
                String nome = Thread.currentThread().getName();
                try {
                    if (mutex.tryLock(timeout, TimeUnit.SECONDS)) {
                        try {
                            System.out.println(nome + " conseguiu o lock e fará a divisão.");
                            int resultado = num1 / num2;
                            System.out.println("Resultado: " + resultado);
                        } finally {
                            // Garante que o lock seja liberado mesmo quando a exceção ocorrer
                            System.out.println(nome + " executando finally e liberando o lock.");
                            mutex.unlock();
                        }
                    } else {
                        System.out.println(nome + " desistiu por timeout.");
                    }
                } catch (Exception e) {
                    System.out.println(nome + " gerou uma exceção: " + e.getMessage());
                }
            };

            Thread[] threads = new Thread[3];
            for (int i = 0; i < 3; i++) {
                threads[i] = new Thread(tarefa, "Thread-" + (i + 1));
                threads[i].start();
            }
            for (Thread t : threads) t.join();

            System.out.print("Deseja executar novamente? (s/n): ");
            continuar = sc.next();
        } while (continuar.equalsIgnoreCase("s"));
    }
}