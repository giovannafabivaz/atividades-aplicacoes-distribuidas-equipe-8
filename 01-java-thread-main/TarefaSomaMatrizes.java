class TarefaSomaMatrizes implements Runnable {
    private String nome;
    private int[][] matrizA;
    private int[][] matrizB;

    public TarefaSomaMatrizes(String nome, int[][] matrizA, int[][] matrizB) {
        this.nome = nome;
        this.matrizA = matrizA;
        this.matrizB = matrizB;
    }

    @Override
    public void run() {
        // O primeiro laço (i) passa pelas linhas
        for (int i = 0; i < matrizA.length; i++) {
            // O segundo laço (j) passa pelas colunas dentro da linha
            for (int j = 0; j < matrizA[i].length; j++) {
                int soma = matrizA[i][j] + matrizB[i][j];
                System.out.println(nome + " - Posição [" + i + "][" + j + "]: Soma = " + soma);
            }
            try {
                Thread.sleep(1000);[cite: 4]
            } catch (InterruptedException e) {}
        }
        System.out.println(nome + " FINALIZADA!");[cite: 4]
    }
}