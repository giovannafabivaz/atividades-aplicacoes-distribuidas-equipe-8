// 1. Criar a classe principal:
public class Main {
    public static void main(String[] args) {
        // 2. Criar instâncias da classe Tarefa:
        Tarefa tarefa1 = new Tarefa("Thread A");
        Tarefa tarefa2 = new Tarefa("Thread B");

        // 3. Passar as tarefas para objetos do tipo Thread:
        Thread t1 = new Thread(tarefa1);
        Thread t2 = new Thread(tarefa2);

        // Iniciar a execução paralela das tarefas (NUNCA chamar o método run() diretamente):
        t1.start();
        t2.start();
    }
}
