class TarefaCalculadora implements Runnable {
    private String nome;
    private int n1, n2;
    private char operacao;

    // Construtor: é como a tarefa recebe as informações iniciais
    public TarefaCalculadora(String nome, int n1, int n2, char operacao) {
        this.nome = nome;
        this.n1 = n1;
        this.n2 = n2;
        this.operacao = operacao;
    }

    @Override
    public void run() {
        // Laço que repete 3 vezes
        for (int i = 1; i <= 3; i++) {
            double resultado = 0;
            
            // Verificamos qual é a operação solicitada
            if (operacao == '+') {
                resultado = (n1 + n2) + i; // Somamos os dois números e adicionamos 'i'
            } else if (operacao == '-') {
                resultado = (n1 - n2) - i;
            } else if (operacao == '*') {
                resultado = (n1 * n2) * i;
            } else if (operacao == '/') {
                resultado = (n1 / n2) / (double)i;
            }

            System.out.println(nome + " - Passo " + i + ": Resultado = " + resultado);

            // Pausa de 1 segundo (1000 milissegundos) para simular trabalho
            try {
                Thread.sleep(1000);[cite: 4]
            } catch (InterruptedException e) {
                System.out.println(nome + " foi interrompida.");[cite: 4]
            }
        }
        System.out.println(nome + " FINALIZADA!");[cite: 4]
    }
}