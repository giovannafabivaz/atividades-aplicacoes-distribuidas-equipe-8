import java.util.concurrent.locks.ReentrantLock;

public class Cinema {
    // Vetor de assentos (false = livre)
    private boolean[] assentos = new boolean[10];
    private ReentrantLock mutex = new ReentrantLock();

    public void reservarAssento(int numero, String nomeCliente) {
        mutex.lock(); // Bloqueia o acesso ao vetor para outras threads
        try {
            System.out.println("[" + nomeCliente + "] está verificando o assento " + numero + "...");
            Thread.sleep(200); // Simulando a lentidão do sistema de compras

            // Verificação dupla: só compra se ainda estiver livre
            if (!assentos[numero]) {
                assentos[numero] = true; // Muda para true (comprado)
                System.out.println("SUCESSO: Assento " + numero + " comprado por " + nomeCliente + "!");
            } else {
                // Se duas threads tentarem comprar juntas, a segunda vai cair aqui ao pegar a tranca
                System.out.println("[" + nomeCliente + "] Assento indisponível");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.unlock(); // Libera o assento para os próximos testarem
        }
    }
}