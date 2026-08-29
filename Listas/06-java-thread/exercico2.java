public class Consumidor {

    private int MAX_THREAD;

    public Consumidor(int MAX_THREAD) {
        this.MAX_THREAD = MAX_THREAD;
    }

    public double consumir() {

        long inicio = System.currentTimeMillis();

        int totalTarefas = 8;
        Thread[] threads = new Thread[MAX_THREAD];

        int tarefaAtual = 0;

        while (tarefaAtual < totalTarefas) {

            int quantidadeThreads = 0;

            // Cria até MAX_THREAD threads
            while (quantidadeThreads < MAX_THREAD && tarefaAtual < totalTarefas) {

                final int numeroTarefa = tarefaAtual + 1;

                threads[quantidadeThreads] = new Thread(() -> {

                    try {
                        System.out.println(
                            "Executando tarefa " + numeroTarefa +
                            " com " + MAX_THREAD + " threads."
                        );

                        // Simula o trabalho da tarefa
                        Thread.sleep(1000);

                        System.out.println(
                            "Tarefa " + numeroTarefa + " concluída."
                        );

                    } catch (InterruptedException e) {
                        System.out.println("Tarefa interrompida.");
                    }
                });

                threads[quantidadeThreads].start();

                quantidadeThreads++;
                tarefaAtual++;
            }

            // Espera as threads terminarem
            for (int i = 0; i < quantidadeThreads; i++) {

                try {
                    threads[i].join();

                } catch (InterruptedException e) {
                    System.out.println("Erro ao aguardar thread.");
                }
            }
        }

        long fim = System.currentTimeMillis();

        return (fim - inicio) / 1000.0;
    }
}

public class Main {

    public static void main(String[] args) {

        Consumidor consumidor2threads = new Consumidor(2);
        Consumidor consumidor4threads = new Consumidor(4);
        Consumidor consumidor6threads = new Consumidor(6);

        System.out.println("===== TESTE COM 2 THREADS =====");
        double tempo2 = consumidor2threads.consumir();

        System.out.println();
        System.out.println("===== TESTE COM 4 THREADS =====");
        double tempo4 = consumidor4threads.consumir();

        System.out.println();
        System.out.println("===== TESTE COM 6 THREADS =====");
        double tempo6 = consumidor6threads.consumir();

        System.out.println();
        System.out.printf("Teste com 2 threads: %.2f segundos%n", tempo2);
        System.out.printf("Teste com 4 threads: %.2f segundos%n", tempo4);
        System.out.printf("Teste com 6 threads: %.2f segundos%n", tempo6);

        // Descobre qual foi mais rápida
        double menorTempo = tempo2;
        int melhorConfiguracao = 2;

        if (tempo4 < menorTempo) {
            menorTempo = tempo4;
            melhorConfiguracao = 4;
        }

        if (tempo6 < menorTempo) {
            menorTempo = tempo6;
            melhorConfiguracao = 6;
        }

        System.out.printf(
            "Configuração mais eficiente: %d threads (%.2fs)%n",
            melhorConfiguracao,
            menorTempo
        );
    }
}