import java.util.concurrent.Semaphore;

public class Estacionamento {
    // Cria um semáforo com 3 permissões (limite de 3 carros simultâneos):
    // Estacionamento com apenas 3 vagas.
    private final Semaphore semaforo = new Semaphore(3);

    public void entrar(String carro) {
        try {
            System.out.println(carro + " chegou e está procurando vaga...");

            // Tenta adquirir uma permissão (vaga). Diminui o contador em 1.
            // Se o contador estiver em 0, a thread fica bloqueada esperando uma vaga liberar.
            semaforo.acquire(); 

            // --- INÍCIO DO ACESSO AO RECURSO ---
            System.out.println(carro + " ESTACIONOU na vaga.");
            
            // Simula o tempo que o carro fica estacionado (2 segundos):
            Thread.sleep(2000); 
            
            System.out.println(carro + " está saindo da vaga...");
            // --- FIM DO ACESSO AO RECURSO ---

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Libera a permissão (vaga). Aumenta o contador em 1.
            // Isso sinaliza e acorda a próxima thread que estava esperando na fila.
            semaforo.release(); 
        }
    }
}
