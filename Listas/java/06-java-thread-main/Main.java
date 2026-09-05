public class Main {
    public static void main(String[] args) {
        int qtdTarefas = 12; 

        // Criar 3 instâncias de consumidor com diferentes tamanhos: 2, 4 e 6 threads (Ex 2)
        Consumidor consumidor2threads = new Consumidor(2);
        Consumidor consumidor4threads = new Consumidor(4);
        Consumidor consumidor6threads = new Consumidor(6);

        // Medir o tempo de execução para cada configuração (Ex 2)
        System.out.println("=== TESTE COM 2 THREADS ===");
        Tarefa[] tarefas2 = Produtor.gerarTarefas(qtdTarefas);
        double tempo2 = consumidor2threads.consumir(tarefas2);

        System.out.println("=== TESTE COM 4 THREADS ===");
        Tarefa[] tarefas4 = Produtor.gerarTarefas(qtdTarefas);
        double tempo4 = consumidor4threads.consumir(tarefas4);

        System.out.println("=== TESTE COM 6 THREADS ===");
        Tarefa[] tarefas6 = Produtor.gerarTarefas(qtdTarefas);
        double tempo6 = consumidor6threads.consumir(tarefas6);

        // Exibir qual configuração foi mais eficiente (Ex 2)
        System.out.println("--- RESULTADO FINAL ---");
        System.out.println("Teste com 2 threads: " + tempo2 + " segundos");
        System.out.println("Teste com 4 threads: " + tempo4 + " segundos");
        System.out.println("Teste com 6 threads: " + tempo6 + " segundos");

        double menorTempo = Math.min(tempo2, Math.min(tempo4, tempo6));
        if (menorTempo == tempo2) {
            System.out.println("Configuração mais eficiente: 2 threads (" + tempo2 + "s)");
        } else if (menorTempo == tempo4) {
            System.out.println("Configuração mais eficiente: 4 threads (" + tempo4 + "s)");
        } else {
            System.out.println("Configuração mais eficiente: 6 threads (" + tempo6 + "s)");
        }
    }
}