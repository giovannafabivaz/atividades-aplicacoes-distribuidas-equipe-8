public class Consumidor {

    private int tarefasExecutadas = 0;

    public void consumir() {

        long inicio = System.currentTimeMillis();

        
        Thread[] tarefas = new Thread[8];

        for (int i = 0; i < tarefas.length; i++) {

            final int numeroTarefa = i + 1;

            tarefas[i] = new Thread(() -> {
                try {
                    System.out.println("Executando tarefa " + numeroTarefa);

            
                    Thread.sleep(1000);

                    System.out.println("Tarefa " + numeroTarefa + " concluída.");

                } catch (InterruptedException e) {
                    System.out.println("Tarefa " + numeroTarefa + " foi interrompida.");
                }
            });

            tarefas[i].start();
        }

       
        for (Thread tarefa : tarefas) {
            try {
                tarefa.join();

                
                tarefasExecutadas++;

            } catch (InterruptedException e) {
                System.out.println("Erro ao aguardar uma tarefa.");
            }
        }

        long fim = System.currentTimeMillis();

        double tempoTotal = (fim - inicio) / 1000.0;

        System.out.println();
        System.out.println("Total de tarefas executadas: " + tarefasExecutadas);
        System.out.printf("Tempo total: %.2f segundos%n", tempoTotal);
    }

    public int getTarefasExecutadas() {
        return tarefasExecutadas;
    }
}


public class Main {

    public static void main(String[] args) {

        System.out.println("Tamanho do vetor de tarefas: 8");
        System.out.println("Número máximo de threads: 3");

        Consumidor consumidor = new Consumidor();

        consumidor.consumir();

        System.out.println("FIM DO PROGRAMA!");
    }
}
