import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Ex8_GerenciadorLog {
    private ReentrantLock mutex = new ReentrantLock();

    public void registrarLog(String mensagem) {
        String nome = Thread.currentThread().getName();
        try {
            // A tentativa de tranca deve usar tryLock(500, TimeUnit.MILLISECONDS)
            if (mutex.tryLock(500, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println("[INÍCIO LOG] " + mensagem);
                    Thread.sleep(800);
                    System.out.println("[FIM LOG]");
                } finally {
                    mutex.unlock();
                }
            } else {
                System.out.println("[" + nome + "] Timeout de arquivo! Log descartado/redirecionado para o console local.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Ex8_GerenciadorLog log = new Ex8_GerenciadorLog();
        
        new Thread(() -> log.registrarLog("Autenticação de usuário"), "Módulo Autenticação").start();
        new Thread(() -> log.registrarLog("Venda processada"), "Módulo Vendas").start();
        new Thread(() -> log.registrarLog("Frete calculado"), "Módulo Frete").start();
    }
}