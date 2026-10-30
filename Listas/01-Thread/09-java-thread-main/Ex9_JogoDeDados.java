import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Ex9_JogoDeDados {
    private static int totalPontos = 0;
    private static ReentrantLock mutex = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String continuar;
        Random random = new Random();

        do {
            System.out.println("\n[Ex9] Iniciando novo jogo...");
            totalPontos = 0;
            
            Runnable tarefa = () -> {
                String nome = Thread.currentThread().getName();
                while (totalPontos < 100) {
                    int dado = random.nextInt(6) + 1; // Número aleatório entre 1 e 6
                    if (mutex.tryLock()) {
                        try {
                            if (totalPontos < 100) {
                                totalPontos += dado;
                                System.out.println("[JOGADA] " + nome + " somou " + dado + ". Total do grupo: " + totalPontos + "/100");
                                if (totalPontos >= 100) {
                                    System.out.println("[VITÓRIA] " + nome + " fez o grupo atingir o objetivo!");
                                }
                            } else {
                                // Se não for menor (o grupo já ganhou): não faça alteração e apenas exiba a mensagem.
                                System.out.println("[FIM DE JOGO] " + nome + " tentou jogar, mas a pontuação máxima já foi atingida.");
                                break;
                            }
                        } finally {
                            mutex.unlock();
                        }
                    }
                    try { Thread.sleep(50); } catch (Exception e) {} // Pausa breve para embaralhar as threads
                }
            };

            Thread[] jogadores = new Thread[4];
            for (int i = 0; i < 4; i++) {
                jogadores[i] = new Thread(tarefa, "Jogador-" + (i + 1));
                jogadores[i].start();
            }
            for (Thread t : jogadores) t.join();

            System.out.print("Deseja jogar novamente? (s/n): ");
            continuar = sc.next();
        } while (continuar.equalsIgnoreCase("s"));
    }
}