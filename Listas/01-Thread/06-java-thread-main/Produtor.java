import java.util.Random;

public class Produtor {
    // O Produtor agora gera as tarefas já sorteando as prioridades (Ex 3)
    public static Tarefa[] gerarTarefas(int quantidade) {
        Tarefa[] tarefas = new Tarefa[quantidade];
        Random random = new Random();
        Prioridade[] prioridades = Prioridade.values();
        
        System.out.println("Tarefas geradas:");
        for (int i = 0; i < quantidade; i++) {
            // Atribui prioridades aleatoriamente às tarefas
            Prioridade p = prioridades[random.nextInt(prioridades.length)];
            tarefas[i] = new Tarefa(i, p);
            System.out.println("Tarefa " + i + " (" + p + ")");
        }
        System.out.println();
        return tarefas;
    }
}