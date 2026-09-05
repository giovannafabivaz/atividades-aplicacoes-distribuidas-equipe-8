import java.util.Arrays;

public class Consumidor {
    private int maxThread; // Parâmetro para pool configurável (Ex 2)
    private int tarefasExecutadas; // Contador de tarefas (Ex 1)
    
    public Consumidor(int maxThread) {
        this.maxThread = maxThread;
    }

    public double consumir(Tarefa[] tarefas) {
        this.tarefasExecutadas = 0;
        int countAlta = 0, countMedia = 0, countBaixa = 0;

        // O Consumidor ordena as tarefas por prioridade antes de executar (Ex 3)
        Arrays.sort(tarefas);

        System.out.println("Executando por prioridade com " + maxThread + " threads:");
        
        // Uso do currentTimeMillis no início (Ex 1)
        long tempoInicio = System.currentTimeMillis();

        // Lógica de blocos (Pool)
        for (int i = 0; i < tarefas.length; i += maxThread) {
            int limite = Math.min(i + maxThread, tarefas.length);
            
            // Dispara as threads do lote
            for (int j = i; j < limite; j++) {
                tarefas[j].start();
            }
            
            // Aguarda e contabiliza
            for (int j = i; j < limite; j++) {
                try {
                    tarefas[j].join(); // Usa o join para esperar
                    
                    // Incrementa quando a tarefa termina com sucesso (Ex 1)
                    this.tarefasExecutadas++;
                    
                    Prioridade p = tarefas[j].getPrioridade();
                    if (p == Prioridade.ALTA) countAlta++;
                    else if (p == Prioridade.MEDIA) countMedia++;
                    else if (p == Prioridade.BAIXA) countBaixa++;
                    
                    // Exibe a prioridade ao executar (Ex 3)[cite: 7]
                    System.out.println("Tarefa " + tarefas[j].getIdTarefa() + " (" + p + ") realizada com sucesso.");
                    
                } catch (InterruptedException e) {
                    System.out.println("Erro ao finalizar tarefa.");
                }
            }
        }

        // Uso do currentTimeMillis no final (Ex 1)
        long tempoFim = System.currentTimeMillis();
        double tempoTotal = (tempoFim - tempoInicio) / 1000.0; // Converte para segundos

        // Conta quantas tarefas de cada prioridade foram executadas (Ex 3)
        System.out.println("\nResumo: " + countAlta + " ALTA, " + countMedia + " MÉDIA, " + countBaixa + " BAIXA");
        
        // Exibe o total de tarefas executadas (Ex 1)
        System.out.println("Total de tarefas executadas: " + this.tarefasExecutadas);
        System.out.println("Tempo total da rodada: " + tempoTotal + " segundos\n");
        
        return tempoTotal;
    }
}