// Implementei Comparable para conseguir ordenar facilmente as prioridades
public class Tarefa extends Thread implements Comparable<Tarefa> {
    private int idTarefa;
    private Prioridade prioridade; // Atributo adicionado (Ex 3)

    public Tarefa(int idTarefa, Prioridade prioridade) {
        this.idTarefa = idTarefa;
        this.prioridade = prioridade;
    }

    public Prioridade getPrioridade() { return prioridade; }
    public int getIdTarefa() { return idTarefa; }

    @Override
    public void run() {
        try {
            // Simula o tempo de execução da tarefa
            Thread.sleep(600);
        } catch (InterruptedException e) {
            System.out.println("Tarefa interrompida.");
        }
    }

    // Regra para o Java saber como ordenar as prioridades
    @Override
    public int compareTo(Tarefa outra) {
        return Integer.compare(this.prioridade.getValor(), outra.prioridade.getValor());
    }
}