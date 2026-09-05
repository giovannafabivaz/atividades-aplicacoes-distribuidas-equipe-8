import java.util.concurrent.locks.ReentrantLock;

public class Ex4_ContaBancaria {
    private double saldo = 1000.0;
    private ReentrantLock mutex = new ReentrantLock();

    public void sacar(double valor, String nomeThread) {
        // O método tryLock() evita esperas infinitas
        if (mutex.tryLock()) {
            try {
                System.out.println(nomeThread + " processando saque...");
                Thread.sleep(1000); // Simula operação
                saldo -= valor;
                System.out.println(nomeThread + " sacou. Saldo atual: " + saldo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.unlock();
            }
        } else {
            // Se retornar false, a thread não deve esperar: ela deve desviar o fluxo
            System.out.println(nomeThread + " desistiu do saque porque a conta estava ocupada por outra operação concorrente.");
        }
    }

    public static void main(String[] args) {
        Ex4_ContaBancaria conta = new Ex4_ContaBancaria();
        Runnable acao = () -> conta.sacar(100, Thread.currentThread().getName());
        
        new Thread(acao, "Thread-A").start();
        new Thread(acao, "Thread-B").start();
    }
}