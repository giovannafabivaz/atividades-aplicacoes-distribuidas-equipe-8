import java.util.Random;

class TarefaAdivinhacao implements Runnable {
    private String nome;

    public TarefaAdivinhacao(String nome) {
        this.nome = nome;
    }

    @Override
    public void run() {
        Random sorteador = new Random();
        int numeroSecreto = sorteador.nextInt(10) + 1; // Sorteia um número de 1 a 10

        System.out.println(nome + " gerou um número secreto! Tentando adivinhar...");

        // Tentamos até 3 palpites
        for (int i = 1; i <= 3; i++) {
            int palpite = sorteador.nextInt(10) + 1; 

            if (palpite == numeroSecreto) {
                System.out.println(nome + " ACERTOU! O número era " + numeroSecreto);
                break; // O 'break' para o laço imediatamente, pois já acertou
            } else if (palpite > numeroSecreto) {
                System.out.println(nome + " tentou " + palpite + ", mas o número é MENOR.");
            } else {
                System.out.println(nome + " tentou " + palpite + ", mas o número é MAIOR.");
            }

            try {
                Thread.sleep(1000);[cite: 4]
            } catch (InterruptedException e) {}
        }
        System.out.println(nome + " FINALIZADA!");[cite: 4]
    }
}