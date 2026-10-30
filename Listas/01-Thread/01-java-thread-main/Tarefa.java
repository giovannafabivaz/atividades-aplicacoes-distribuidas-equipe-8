// 1. Criar uma classe que implementa a interface Runnable:
class Tarefa implements Runnable {
    private String nomeDaTarefa;

    public Tarefa(String nomeDaTarefa) {
        this.nomeDaTarefa = nomeDaTarefa;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(nomeDaTarefa + " processando passo " + i + ".");
            try {
                // 2. Simular uma tarefa que demora 1 segundo:
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                System.out.println(nomeDaTarefa + " foi interrompida.");
            }
        }
        System.out.println(nomeDaTarefa + " FINALIZADA!");
    }
}
