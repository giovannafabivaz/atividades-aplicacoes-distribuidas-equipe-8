import java.util.concurrent.locks.ReentrantLock;

public class Ex7_GeradorIdentificador {
    private int proximoId = 1;
    private ReentrantLock mutex = new ReentrantLock();

    public void obterProximoId() {
        String nome = Thread.currentThread().getName();
        boolean sucesso = false;
        
        while (!sucesso) {
            if (mutex.tryLock()) {
                try {
                    int idAtual = proximoId;
                    proximoId++;
                    System.out.println(nome + " pegou o número: " + idAtual);
                    sucesso = true;
                } finally {
                    mutex.unlock();
                }
            } else {
                // Se não conseguir o lock (retornar false), deve exibir falha e tentar novamente
                System.out.println("[" + nome + "] Falha ao gerar ID: barramento de memória ocupado. Tentando novamente...");
            }
        }
    }

    public static void main(String[] args) {
        Ex7_GeradorIdentificador gerador = new Ex7_GeradorIdentificador();
        Runnable acao = gerador::obterProximoId;

        for (int i = 1; i <= 5; i++) {
            new Thread(acao, "Thread-" + i).start();
        }
    }
}