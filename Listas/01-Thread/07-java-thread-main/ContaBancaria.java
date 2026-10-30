import java.util.concurrent.locks.ReentrantLock;

public class ContaBancaria {
    private double saldo = 100.0;
    // O Mutex funciona como o cadeado da nossa seção crítica
    private ReentrantLock mutex = new ReentrantLock();

    public void sacar(double valor, String nomeThread) {
        // Exercício 1: Substituímos o lock() por tryLock()
        // Se retornar true, ele conseguiu a tranca. Se false, a conta já está ocupada.
        if (mutex.tryLock()) {
            try {
                System.out.println("[" + nomeThread + "] acessou a conta e iniciou o saque.");
                if (saldo >= valor) {
                    Thread.sleep(100); // Simulando o tempo de processamento
                    saldo -= valor;
                    System.out.println("[" + nomeThread + "] sacou R$" + valor + ". Saldo atual: R$" + saldo);
                } else {
                    System.out.println("[" + nomeThread + "] falhou: saldo insuficiente.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // É obrigatório liberar a tranca no finally para não travar o sistema
                mutex.unlock();
            }
        } else {
            // Se o tryLock deu false, a thread desiste imediatamente e imprime a mensagem exigida
            System.out.println("[" + nomeThread + "] desistiu do saque porque a conta estava ocupada por outra operação.");
        }
    }
}