import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("=== EXERCÍCIO 1: ESTACIONAMENTO VIP (FAIRNESS) ===");
        // O parâmetro 'true' define a justiça (fairness)
        // EXPLICAÇÃO DA POLÍTICA FIFO: Ao passar 'true', o Java força que a fila de threads bloqueadas 
        // seja estritamente FIFO (First-In, First-Out). Isso significa que os carros que chegaram e 
        // pediram o acquire() primeiro terão prioridade garantida na vaga, impedindo que uma thread 
        // nova atravesse a fila.
        Semaphore semaforoEstacionamento = new Semaphore(2, true); 
        for (int i = 1; i <= 5; i++) {
            new Thread(new Estacionamento("Carro-" + i, semaforoEstacionamento)).start();
            // Dispara os carros com pequenos intervalos de tempo
            Thread.sleep(100); 
        }
        Thread.sleep(3000); 

        System.out.println("\n=== EXERCÍCIO 2: RATE LIMITER DA API ===");
        // Servidor processa 3 consultas ao mesmo tempo
        Semaphore semaforoAPI = new Semaphore(3); 
        // Dispara 10 threads simulando clientes
        for (int i = 1; i <= 10; i++) {
            new Thread(new ClienteAPI(i, semaforoAPI)).start();
        }
        Thread.sleep(4000);

        System.out.println("\n=== EXERCÍCIO 3: PONTE ESTREITA ===");
        // Semáforo Binário inicializado com 1 permissão
        Semaphore semaforoPonte = new Semaphore(1); 
        new Thread(new CarroPonte("Esquerda", semaforoPonte)).start();
        new Thread(new CarroPonte("Direita", semaforoPonte)).start();
        new Thread(new CarroPonte("Esquerda", semaforoPonte)).start();
        Thread.sleep(3000);

        System.out.println("\n=== EXERCÍCIO 4: LABORATÓRIO DE INFORMÁTICA ===");
        // Semáforo com 5 computadores disponíveis
        Semaphore semaforoLab = new Semaphore(5); 
        // Dispara 8 threads de alunos
        for (int i = 1; i <= 8; i++) { 
            new Thread(new AlunoLaboratorio("Aluno-" + i, semaforoLab)).start();
        }
        Thread.sleep(4000);

        System.out.println("\n=== EXERCÍCIO 5: BUFFET DE RESTAURANTE ===");
        // Semáforo permite no máximo 4 pessoas na pista
        Semaphore semaforoBuffet = new Semaphore(4); 
        for (int i = 1; i <= 10; i++) {
            new Thread(new ClienteBuffet("Cliente-" + i, semaforoBuffet)).start();
        }
    }
}