public class Main {
    public static void main(String[] args) {
        
        // DADOS PARA OS EXERCÍCIOS 4 E 5 
        // vetores para o exercício 4
        int[] vetorA = {10, 20, 30};
        int[] vetorB = {1, 2, 3};

        // matrizes (tabelas 2x2) para o exercício 5
        int[][] matrizA = { {1, 2}, {3, 4} };
        int[][] matrizB = { {5, 6}, {7, 8} };


        // 1. Criar instâncias das classes de Tarefa (uma para cada exercício):
        TarefaCalculadora tarefaCalc = new TarefaCalculadora("Thread Calc", 10, 5, '+');
        TarefaAdivinhacao tarefaAdiv = new TarefaAdivinhacao("Thread Adivinha");
        TarefaContador tarefaCont = new TarefaContador("Thread Contador", "Estudando Java");
        TarefaSomaVetores tarefaVet = new TarefaSomaVetores("Thread Vetores", vetorA, vetorB);
        TarefaSomaMatrizes tarefaMat = new TarefaSomaMatrizes("Thread Matrizes", matrizA, matrizB);


        // Passar as tarefas para objetos do tipo Thread:
        Thread t1 = new Thread(tarefaCalc);
        Thread t2 = new Thread(tarefaAdiv);
        Thread t3 = new Thread(tarefaCont);
        Thread t4 = new Thread(tarefaVet);
        Thread t5 = new Thread(tarefaMat);


        // Iniciar a execução paralela das tarefas (NUNCA chamar o método run() diretamente):
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}